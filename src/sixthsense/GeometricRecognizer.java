package sixthsense;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class GeometricRecognizer {

	public static final int NUMRESAMPLEPOINTS = 64;
	private static final double DX = 250.0;
	public static final SizeR RESAMPLESCALE = new SizeR(DX, DX);
	public static final double DIAGONAL = Math.sqrt(DX * DX + DX * DX);
	public static final double HALFDIAGONAL = 0.5 * DIAGONAL;
	public static final PointR RESAMPLEORIGIN = new PointR(0,0);
	// Golden Ratio
	private static final double PHI = 0.5 * (-1 + Math.sqrt(5));

	//dc
	public double tolerance = 0.75;

	// batch testing
	private static final int NUMRANDOMTESTS = 100;

	//public event ProgressEventHandler ProgressChangedEvent;

	private Hashtable<String, Gesture> gestures;

	public GeometricRecognizer() {
		gestures = new Hashtable<String, Gesture>(256);
	}

	// Candidate points
	public NBestList recognize(List<PointR> points) {

		// resample to a common number of points
		points = PointR.resample(points, NUMRESAMPLEPOINTS);

		// rotate so that the centroid-to-1st-point is at zero degrees
		// Indicative angle
		double radians = PointR.getAngleInRadians(PointR.getCentroid(points), (PointR) points.get(0), false);
		// Undo angle
		points = PointR.rotateByRadians(points, -radians);

		// Scale to a common (square) dimension
		points = PointR.scaleTo(points, RESAMPLESCALE);

		// translate to a common origin
		points = PointR.translateCentroidTo(points, RESAMPLEORIGIN);

		NBestList nbest = new NBestList();
		Gesture p = null;
		while (gestures.values().iterator().hasNext()) {
			p = (Gesture) gestures.values().iterator().next();
			double[] best = goldenSectionSearch(
					// to rotate
					points, 
					// to match
					p.getPoints(), 
					// lbound
					PointR.deg2Rad(-45.0),
					// ubound
					PointR.deg2Rad(+45.0),
					// threshold
					PointR.deg2Rad(2.0));
			double score = 1d - best[0] / HALFDIAGONAL;
			// name, score, distance, angle
			nbest.addResult(p.getName(), score, best[0], best[1]);
		}
		nbest.addResult("Try Again", tolerance, 0.0, 0.0);
		// Sort so that nbest[0] is best result
		nbest.sortDescending();
		return nbest;
	}

	private double[] goldenSectionSearch(List<PointR> pts1, List<PointR> pts2, double a, double b, double threshold) {

		double x1 = PHI * a + (1 - PHI) * b;
		List<PointR> newPoints = PointR.rotateByRadians(pts1, x1);
		double fx1 = PointR.getPathDistance(newPoints, pts2);

		double x2 = (1 - PHI) * a + PHI * b;
		newPoints = PointR.rotateByRadians(pts1, x2);
		double fx2 = PointR.getPathDistance(newPoints, pts2);

		// Calls
		double i = 2.0;
		while (Math.abs(b - a) > threshold) {
			if (fx1 < fx2) {
				b = x2;
				x2 = x1;
				fx2 = fx1;
				x1 = PHI * a + (1- PHI) * b;
				newPoints = PointR.rotateByRadians(pts1, x1);
				fx1 = PointR.getPathDistance(newPoints, pts2);
			}
			else {
				a = x1;
				x1 = x2;
				fx1 = fx2;
				x2 = (1 - PHI) * a + PHI * b;
				newPoints = PointR.rotateByRadians(pts1, x2);
				fx2 = PointR.getPathDistance(newPoints, pts2);
			}
			i++;
		}
		// distance, angle, calls to pathdist
		double x[] = { Math.min(fx1, fx2), PointR.rad2Deg((b + a) / 2.0), i };
		return x;
	}

	// continues to rotate 'pts1' by 'step' degrees as long as points become ever-closer 
	// in path-distance to pts2. the initial distance is given by D. the best distance
	// is returned in array[0], while the angle at which it was achieved is in array[1].
	// array[3] contains the number of calls to PathDistance.

	private double[] hillClimbSearch(List<PointR> pts1, List<PointR> pts2, double D, double step) {
		double i = 0.0;
		double theta = 0.0;
		double d = D;
		do {
			// the last angle tried was better still
			D = d;
			theta += step;
			List<PointR> newPoints = PointR.rotateByDegrees(pts1, theta);
			d = PointR.getPathDistance(newPoints, pts2);
			i++;
		}
		while (d <= D);
		// distance, angle, calls to pathdist
		double x[] = { D, theta - step, i };
		return x;
	}

	private double[] fullSearch(List<PointR> pts1, List<PointR> pts2, FileWriter writer) {
		double bestA = 0d;
		double bestD = PointR.getPathDistance(pts1, pts2);

		for (int i = -180; i <= +180; i++) {
			List<PointR> newPoints = PointR.rotateByDegrees(pts1, i);
			double d = PointR.getPathDistance(newPoints, pts2);
			if (writer != null) {
				try {
					writer.write(i + "\t" + new DecimalFormat("##.##").format(Math.round(d)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (d < bestD) {
				bestD = d;
				bestA = i;
			}
		}
		try {
			writer.write("\nFull Search (360 rotations)\n" + new DecimalFormat("##.##").format(Math.round(bestA)) + (char) 176 + "\t" +new DecimalFormat("##.###").format(Math.round(bestD)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// distance, angle, calls to pathdist
		double x[] = { bestD, bestA, 360.0 };
		return x;
	}

	public int getNumGestures() {
		return gestures.size();
	}

	public void clearGestures() {
		gestures.clear();
	}
	public List<Gesture> getGestures() {
		List<Gesture> list = new ArrayList<Gesture>(gestures.values());
		Collections.sort(list);
		return list;
	}

	public boolean saveGesture(String filename, List<PointR> points) {

		// add the new protorype with the name etracted from the filename.
		String name = Gesture.parseName(filename);
		if (gestures.containsKey(name))
			gestures.remove(name);
		Gesture newPrototype = new Gesture(name, points);
		gestures.put(name, newPrototype);

		// figure out the duration of the gesture
		PointR p0 = (PointR) points.get(0);
		PointR pn = (PointR) points.get(points.size()-1);

		// do the xml writing
		boolean success = true;

		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = null;
		try {
			writer = factory.createXMLStreamWriter(new FileWriter(filename));

			// save the prototype as an Xml file
			writer.writeStartDocument();
			writer.writeStartElement("Gesture");
			writer.writeAttribute("Name", name);
			writer.writeAttribute("NumPts", String.valueOf(points.size()));
			writer.writeAttribute("Millseconds", String.valueOf(pn.getT() - p0.getT()));
			writer.writeAttribute("AppName", name);
			writer.writeAttribute("Name", name);
			writer.writeAttribute("AppVer", name);
			writer.writeAttribute("Date", new Date().toString());
			writer.writeAttribute("TimeOfDay", new Date().toString());


			// write out the raw individual points
			for (PointR p : points) {

				writer.writeStartElement("Point");
				writer.writeAttribute("X", String.valueOf(p.getX()));
				writer.writeAttribute("Y", String.valueOf(p.getY()));
				writer.writeAttribute("T", String.valueOf(p.getT()));
				// <Point />
				writer.writeEndElement();
			}
			// </Gesture>
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
			success = false;
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (XMLStreamException e) {
					e.printStackTrace();
					success = false;
				}
			}
		}

		// Xml file successfully written (or not)
		return success;
	}

	public boolean loadGesture(String filename) {

		boolean success = true;

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		try {
			reader = factory.createXMLStreamReader(new FileReader(filename));
			Gesture p = readGesture(reader);

			// remove any with the same name and add the prototype gesture
			if (gestures.containsKey(p.getName())) {
				gestures.remove(p.getName());
			}
			gestures.put(p.getName(), p);
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
			success = false;
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (XMLStreamException e) {
					e.printStackTrace();
					success = false;
				}
			}
		}
		return success;
	}

	// assumes the reader has been just move to the head of the content.
	private Gesture readGesture(XMLStreamReader reader) {
		return null;

		/*
		assert(reader.getLocalName().equals("Gesture"));
		String name = reader.getattribute();

		List<PointR> points = new ArrayList<PointR>(reader.getatt)


				return new Gesture();
		 */
	}

	/** <summary>
	 *  Assemble the gesture filenames into categories that contain 
	 *  potentially multiple examples of the same gesture.
	 *  </summary>
	 *  <param name="filenames"></param>
	 *  <returns>A 1D list of category instances that each
	 *  contain the same number of examples, or <b>null</b> if an error occurs.
	 *  </returns>
	 *  <remarks>
	 *  See the comments above MainForm.BatchProcess_Click.
	 * </remarks>
	 **/
	public List<Category> assembleBatch(String filenames[]) {

		Hashtable<String, Category> categories = new Hashtable<String, Category>();

		for (int i = 0; i < filenames.length; i++) {
			String filename = filenames[i];

			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = null;
			try {
				reader = factory.createXMLStreamReader(new FileReader(filename));


				Gesture p = readGesture(reader);
				String catName = Category.parseName(p.getName());
				if (categories.containsKey(catName)) {
					Category cat = (Category) categories.get(catName);
					// if the category has been made before, just add to it.
					try {
						cat.addExample(p);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					// create new category
					Category tmp = null;
					try {
						tmp = new Category (catName, p);
					} catch (Exception e) {
						e.printStackTrace();
					}
					categories.put(catName, tmp);
				}
			}
			catch (XMLStreamException | IOException e) {
				e.printStackTrace();
				categories.clear();
				categories = null;
			}
			finally {
				if (reader != null) {
					try {
						reader.close();
					}
					catch (XMLStreamException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// now make sure that each category has the same number of elements in it
		List<Category> list = null;
		if (categories != null) {
			list = new ArrayList<Category>(categories.values());
			int numExamples = ((Category) list.get(0)).getExamples();
			for (Category c : list) {
				if (c.getExamples() != numExamples) {
					System.out.println("Different number of examples in gesture categories.");
					list.clear();
					list = null;
					break;
				}
			}
		}
		return list;
	}

	/** <summary>
	 * Tests an entire batch of files. See comments atop MainForm.TestBatch_Click().
	 * </summary>
	 * <param name="subject">Subject number.</param>
	 * <param name="speed">"fast", "medium", or "slow"</param>
	 * <param name="categories">A list of gesture categories that each contain lists of
	 * prototypes (examples) within that gesture category.</param>
	 * <param name="dir">The directory into which to write the output files.</param>
	 * <returns>True if successful; false otherwise.
	 * </returns>
	 **/
	public boolean testBatch(int subject, String speed, List<Category> categories, String dir) {
		boolean success = true;
		FileWriter mainWriter = null;
		FileWriter recWriter = null;

		// Set up a main results file and detailed recognition results file
		int start = 0; //Environment.TickCount;

		String mainFile = dir + "geometric_main_" + start + ".txt";
		String recFile = dir + "geometric_data_" + start + ".txt";

		try {
			mainWriter = new FileWriter(mainFile, false);
			mainWriter.write("Subject = " + subject + ", Recognizer = geometric, Speed = " + speed + ", StartTime(ms) = " + start);
			mainWriter.write("Subject Recognizer Speed NumTraining GestureType RecognitionRate\n");

			recWriter = new FileWriter(recFile, false);
			recWriter.write("Subject = " + subject + ", Recognizer = geometric, Speed = " + speed + ", StartTime(ms) = " + start);
			recWriter.write("Correct? NumTrain Tested 1stCorrect Pts Ms Angle : (NBestNames) [NBestScores]\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Determine the number of gesture categories and the number of examples in each one.
		int numCategories = categories.size();
		int numExamples = ((Category) categories.get(0)).getExamples();
		double totalTests = (numExamples - 1) * NUMRANDOMTESTS;

		// Outermost loop: trains on N=1..9, tests on 10-N (for e.g., numExamples = 10)
		for (int n = 1; n <= numExamples - 1; n++) {
			// Storage for the final avg results for eache category for this N
			double results[] = new double[numCategories];

			// Run a number of tests at this particular N number of training examples
			for (int r = 0; r < NUMRANDOMTESTS; r++) {
				// Clear any (old) loaded prototypes
				gestures.clear();

				// Load (train on) N randomly selected gestures in each category
				for (int i = 0; i < numCategories; i++) {
					// The category to load N examples for
					Category c = (Category) categories.get(i);
					// Select N unique indices
					Integer[] chosen;
					List<Integer> tmplist = new ArrayList<Integer>();
					for (int a = 0; a < n; a++) {
						tmplist.add((int) (Math.random() * numExamples - 1));
					}
					Collections.shuffle(tmplist);
					chosen = (Integer[]) tmplist.toArray();


					for (int j = 0; j < chosen.length; j++) {
						// Get the prototype from this category at chosen[j]
						Gesture p = null;
						try {
							p = c.getGesture(chosen[j]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// Load the randomly selected test gesture into the recognizer
						gestures.put(p.getName(), p);
					}
				}
				// Testing loop on all unloaded gestures in each category. Creates a recognition
				// rate (%) by averaging the binary outcomes (correct, incorrect) for each test.

				for (int i = 0; i < numCategories; i++) {

					// pick a random unloaded gesture in this category for testing
					// instead of dumbly picking, first find out what indices aren't
					// loaded, and then randomly pick from those.
					Category c = (Category) categories.get(i);
					int[] notLoaded = new int[numExamples - n];
					for (int j = 0, k = 0; j < numExamples; j++) {
						Gesture g = null;
						try {
							g = c.getGesture(j);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (!gestures.containsKey(g.getName())) {
							// jth gesture in c is not loaded
							notLoaded[k++] = j;
						}
					}
					// index
					int chosen = (int) (Math.random() * notLoaded.length - 1);
					// gesture to test
					Gesture p = null;
					try {
						p = c.getGesture(notLoaded[chosen]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					assert(gestures.containsKey(p.getName()));

					// Do the recognition !
					List<PointR> testPts = PointR.rotateByDegrees(p.getRawPoints(), Math.random() * 360);
					NBestList result = recognize(testPts);
					String category = Category.parseName(result.getName());
					int correct = (c.getName() == category) ? 1 : 0;

					try {
						recWriter.write(String.valueOf(correct) + " " + String.valueOf(n) + " " + 
								p.getName() + " " + firstCorrect(p.getName(), result.getNames()) + " " + 
								String.valueOf(p.getRawPoints().size()) + " " + String.valueOf(p.getDuration()) +
								" " + new DecimalFormat("##.#").format(Math.round(result.getAngle())) + (char) 176 + 
								" : (" + result.getNamesString() + ") [" + result.getScoresString() + "]");
					} catch (IOException e) {
						e.printStackTrace();
					}

					results[i] += correct;
				}

				// provide feedback as to how many tests have been performed thus far.
				double testsSoFar = ((n - 1) * NUMRANDOMTESTS) + r;
				// callback
				//TODO: Manque une ligne
				// ProgressChangedEvent(this, new ProgressEventArgs(testsSoFar / totalTests));
			}

			//
			// now create the final results for this N and write them to a file
			//
			for (int i = 0; i < numCategories; i++) {
				// normalize by the number of tests at this N
				results[i] /= (double) NUMRANDOMTESTS;
				Category c = (Category) categories.get(i);
				// Subject Recognizer Speed NumTraining GestureType RecognitionRate
				try {
					mainWriter.write(subject + " geomtric " + speed + " " + n + " " + c.getName() + " " + new DecimalFormat("##.###").format(Math.round(results[i])));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// time-stamp the end of the processing
		int end = (int) System.currentTimeMillis();
		try {
			mainWriter.write("\nEndTime(ms) = " + end + ", Minutes = " + new DecimalFormat("##.##").format(Math.round(end - start / 60000.0)));
			recWriter.write("\nEndTime(ms) = " + end + ", Minutes = " + new DecimalFormat("##.##").format(Math.round(end - start / 60000.0)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (mainWriter != null) {
			try {
				mainWriter.close();
				recWriter.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	private int firstCorrect(String name, String[] names) {
		String category = Category.parseName(name);
		for (int i = 0; i <names.length; i++) {
			String c = Category.parseName(names[i]);
			if (category.equals(c)) {
				return i + 1;
			}
		}
		return -1;
	}

	public boolean CreateRotationGraph(String file1, String file2, String dir, boolean similar)
	{
		boolean success = true;
		FileWriter writer = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		try {
			// read gesture file #1
			reader = factory.createXMLStreamReader(new FileReader(file1));
			Gesture g1 = readGesture(reader);
			reader.close();

			// read gesture file #2
			reader = factory.createXMLStreamReader(new FileReader(file2));
			Gesture g2 = readGesture(reader);

			// create output file for results
			String outfile = dir + "\\" + (similar ? "o" : "x") + "(" + g1.getName() + ", " + g2.getName() + ")_" + (int)System.currentTimeMillis() + ".txt";
			writer = new FileWriter(outfile, false);
			writer.write("Rotated: " + g1.getName() + " --> " + g2.getName() + ". " + new Date().toString() + ", " + new Date().toString() + "\n");

			// do the full 360 degree rotations
			double[] full = fullSearch(g1.getPoints(), g2.getPoints(), writer);

			// use bidirectional hill climbing to do it again
			// initial distance
			double init = PointR.getPathDistance(g1.getPoints(), g2.getPoints());
			double[] pos = hillClimbSearch(g1.getPoints(), g2.getPoints(), init, 1d);
			double[] neg = hillClimbSearch(g1.getPoints(), g2.getPoints(), init, -1d);
			double[] best = new double[3];
			// min distance
			best = (neg[0] < pos[0]) ? neg : pos;
			double tmp = pos[2] + neg[2] + 1;
			writer.write("\nHill Climb Search (" + tmp + " rotations)\n" +  new DecimalFormat("##.##").format(Math.round(best[1])) + (char) 176 
					+ "\t" +  new DecimalFormat("##.###").format(Math.round(best[0])) + "px");

			// use golden section search to do it yet again
			double[] gold = goldenSectionSearch(
					g1.getPoints(),              // to rotate
					g2.getPoints(),              // to match
					PointR.deg2Rad(-45.0),   // lbound
					PointR.deg2Rad(+45.0),   // ubound
					PointR.deg2Rad(2.0));    // threshold

			// calls, angle, distance
			writer.write("\nGolden Section Search (" + gold[2] + " rotations)\n" +  new DecimalFormat("##.##").format(Math.round(gold[1])) + (char) 176 
					+ "\t" +  new DecimalFormat("##.###").format(Math.round(gold[0])) + "px");

			// for pasting into Excel
			// In order :
			// 1. rotated
			// 2. into
			// 3. |angle|
			// 4. Full Search angle
			// 5. Full Search distance
			// 6. Initial distance w/o any search
			// 7. Full Search iterations
			// 8. |angle|
			// 9. Bidirectional Hill Climb Search angle
			// 10. Bidirectional Hill Climb Search distance
			// 11. Bidirectional Hill Climb Search iterations
			// 12. |angle|
			// 13. Golden Section Search angle
			// 14. Golden Section Search distance
			// 15. Golden Section Search iterations
			writer.write("\n" + g1.getName() + " " + g2.getName() + " " + 
					new DecimalFormat("##.##").format(Math.abs(Math.round(full[1]))) + " " +
					new DecimalFormat("##.##").format(Math.round(full[1])) + " " + 
					new DecimalFormat("##.###").format(Math.round(full[0])) + " " + 
					new DecimalFormat("##.###").format(Math.round(init)) + " " + 
					full[2] + " " + 
					new DecimalFormat("##.##").format(Math.abs(Math.round(best[1]))) + " " + 
					new DecimalFormat("##.##").format(Math.round(best[1])) + " " + 
					new DecimalFormat("##.###").format(Math.round(best[0])) + " " + 
					(pos[2] + neg[2] + 1) + " " + 
					new DecimalFormat("##.##").format(Math.abs(Math.round(gold[1]))) + " " + 
					new DecimalFormat("##.##").format(Math.round(gold[1])) + " " + 
					new DecimalFormat("##.###").format(Math.round(gold[0])) + " " + 
					gold[2]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			success = false;
		}
		finally
		{
			if (reader != null) {
				try {
					reader.close();
				} catch (XMLStreamException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
}