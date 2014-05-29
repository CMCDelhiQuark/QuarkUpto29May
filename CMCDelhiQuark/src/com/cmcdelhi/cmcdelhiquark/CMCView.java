package com.cmcdelhi.cmcdelhiquark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import cm.cmcdelhi.dialog.CMCDialogCreator;

import com.cmcdelhi.notification.CMCNotificationGenerator;
import com.facebook.android.FacebookError;
import com.facebook.model.GraphUser;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CMCView extends View implements SensorEventListener {

	// Add IT Realted News in the App

	Star[] starCollection = new Star[50];
	int[] squareCollection = new int[20];
	LockedColorSingleton lcs;

	private final SensorManager mSensorManager;
	private final Sensor mAccelerometer;

	float accXPrev, accYPrev, accZPrev;
	float accX, accY, accZ;

	int cmcViewHeigth = 0;
	int cmcViewWidth = 0;
	// lineOne
	int lineOneStartX = 0;
	int lineOneStartY = 0;
	int lineOneEndX = 0;
	int lineOneEndY = 0;

	// lineTwo
	int lineTwoStartX = 0;
	int lineTwoStartY = 0;
	int lineTwoEndX = 0;
	int lineTwoEndY = 0;

	// lineThree
	int lineThreeStartX = 0;
	int lineThreeStartY = 0;
	int lineThreeEndX = 0;
	int lineThreeEndY = 0;

	// lineFour
	int lineFourStartX = 0;
	int lineFourStartY = 0;
	int lineFourEndX = 0;
	int lineFourEndY = 0;

	// lineFive
	int lineFiveStartX = 0;
	int lineFiveStartY = 0;
	int lineFiveEndX = 0;
	int lineFiveEndY = 0;

	// lineSix
	int lineSixStartX = 0;
	int lineSixStartY = 0;
	int lineSixEndX = 0;
	int lineSixEndY = 0;

	// random faint line

	// current touched location
	int currentX = 0;
	int currentY = 0;

	Context currentContext;

	// Courses Icon
	Bitmap bmpCourses;
	Bitmap courseScaled;

	// User Icon
	Bitmap bmpUserPic;
	Bitmap userPicScaled;

	// Facebook icon
	Bitmap bmpFacebook;
	Bitmap facebookScaled;

	// Locate Map icon
	Bitmap bmpMap;
	Bitmap mapScaled;

	// Sudent Call icon
	Bitmap bmpStudentCall;
	Bitmap studentCallScaled;
	// Admin Call icon
	Bitmap bmpAdminCall;
	Bitmap adminCallScaled;
	// Browser icon
	Bitmap bmpBrowser;
	Bitmap browserScaled;

	// Mail Icon
	Bitmap bmpMail;
	Bitmap mailScaled;

	// Mail Icon
	Bitmap cmcLogo;
	Bitmap cmcLogoScaled;

	Rect cmcHeaderRect;

	// enquiriesRect, tataEnterprise

	// colors array

	public CMCView(Context context) {

		super(context);

		loadImage();

		lcs = LockedColorSingleton.getInstance();
		currentContext = context;

		mSensorManager = (SensorManager) context
				.getSystemService(Service.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		setBackgroundColor(Color.BLACK);

		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);

		// course icon initialisation
		bmpCourses = BitmapFactory.decodeResource(getResources(),
				R.raw.ic_action_copy);
		courseScaled = Bitmap.createScaledBitmap(bmpCourses, 180, 180, true);

		// Facebook icon initialisation
		bmpFacebook = BitmapFactory.decodeResource(getResources(),
				R.raw.facebook);
		facebookScaled = Bitmap.createScaledBitmap(bmpFacebook, 60, 60, true);

		// Browser icon initialisation
		bmpBrowser = BitmapFactory.decodeResource(getResources(), R.raw.chrome);
		browserScaled = Bitmap.createScaledBitmap(bmpBrowser, 60, 60, true);

		// Student Call Icon intitialisation
		bmpStudentCall = BitmapFactory.decodeResource(getResources(),
				R.raw.ic_action_call);
		studentCallScaled = Bitmap.createScaledBitmap(bmpStudentCall, 110, 110,
				true);

		// Locate Map icon intitialisation
		bmpMap = BitmapFactory.decodeResource(getResources(),
				R.raw.ic_action_locate);
		mapScaled = Bitmap.createScaledBitmap(bmpMap, 100, 100, true);

		// Mail Icon
		bmpMail = BitmapFactory.decodeResource(getResources(),
				R.raw.ic_action_mail);
		mailScaled = Bitmap.createScaledBitmap(bmpMail, 100, 100, true);

		// CMC Logo Icon
		cmcLogo = BitmapFactory.decodeResource(getResources(),
				R.raw.cmcquarkpng);
		cmcLogoScaled = Bitmap.createScaledBitmap(cmcLogo, 208, 130, true);

		// obtaining the height and with of the device
		// new MainActivity().getWindowManager().getDefaultDisplay().getWidth();

		cmcViewHeigth = ((Activity) context).getWindowManager()
				.getDefaultDisplay().getHeight();

		cmcViewWidth = ((Activity) context).getWindowManager()
				.getDefaultDisplay().getWidth();

		// randomColorVal=1;

		// setting the color
		// Color cbackgorund = new Color();
		// cbackgorund.argb(255,63, 122, 182);
		super.setBackgroundColor(lcs.colorVal);

		// line One coordinates
		lineOneStartX = (int) (0.60 * cmcViewWidth);
		lineOneStartY = (int) (0.10 * cmcViewHeigth);
		lineOneEndX = lineOneStartX;
		lineOneEndY = (int) (0.50 * cmcViewHeigth);

		// line Two Coordinates

		lineTwoStartX = (int) (0.12 * cmcViewWidth);
		lineTwoStartY = (int) (0.10 * cmcViewHeigth);// lineTwoStartY=lineOneStartY
		lineTwoEndX = cmcViewWidth - (int) (0.04 * cmcViewHeigth);
		lineTwoEndY = lineTwoStartY;

		// line Three Coordinates
		// lineThree
		lineThreeStartX = (int) (0.40 * cmcViewWidth);
		lineThreeStartY = (int) (0.35 * cmcViewHeigth);
		lineThreeEndX = lineOneStartX;
		lineThreeEndY = lineThreeStartY;

		// lineFive
		lineFiveStartX = (int) (0.90 * cmcViewWidth);
		lineFiveStartY = (int) (0.40 * cmcViewHeigth);
		lineFiveEndX = cmcViewWidth;
		lineFiveEndY = lineFiveStartY;

		// lineSix
		lineSixStartX = (int) (0.90 * cmcViewWidth);
		lineSixStartY = (int) (0.50 * cmcViewHeigth);
		lineSixEndX = cmcViewWidth;
		lineSixEndY = lineSixStartY;

		// lineFour Cooordinates
		lineFourStartX = cmcViewWidth - (int) (0.15 * cmcViewWidth);
		lineFourStartY = lineSixStartY + 40;
		lineFourEndX = lineFourStartX;
		lineFourEndY = cmcViewHeigth;

		cmcHeaderRect = new Rect(0, (int) (0.03 * cmcViewHeigth), cmcViewWidth,
				(int) (0.20 * cmcViewHeigth));

		// enquiriesRect = new Rect((int) (0.65 * cmcViewWidth), 0,
		// (int) (0.77 * cmcViewWidth), (int) (0.20 * cmcViewHeigth));

		// tataEnterprise = new Rect((int) (0.83 * cmcViewWidth),
		// (int) (0.03 * cmcViewHeigth), cmcViewWidth,
		// (int) (0.20 * cmcViewHeigth));

		Toast.makeText(context, cmcViewHeigth + " , " + cmcViewWidth,
				Toast.LENGTH_LONG).show();
		// Toast.makeText(context, lineOneStartX+"", Toast.LENGTH_LONG).show();
		// Toast.makeText(context, lineOneStartY+"", Toast.LENGTH_LONG).show();
		// Toast.makeText(context, lineOneEndX +"", Toast.LENGTH_LONG).show();
		// Toast.makeText(context, lineOneEndY+"", Toast.LENGTH_LONG).show();

		// Gradiant Cirular

		Drawable[] layers = new Drawable[1];

		ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
			@Override
			public Shader resize(int width, int height) {
				// LinearGradient lg = new LinearGradient(0, 0, cmcViewWidth, 0,
				// new int[] { colorArray[finalRandomColorVal],
				// colorArray[finalRandomColorVal],
				// colorArray[finalRandomColorVal], Color.BLACK },
				// new float[] { 0, 0.49f, 0.50f, 1 },
				// Shader.TileMode.CLAMP);
				//
				// return lg;

				RadialGradient rd;

				if (cmcViewHeigth > cmcViewWidth) {

					// purana color
					// rd = new RadialGradient(cmcViewWidth / 2, cmcViewHeigth,
					// cmcViewHeigth / 2, Color.argb(255, 26, 40, 67),
					// Color.argb(255, 63, 122, 182),
					// Shader.TileMode.CLAMP);

					rd = new RadialGradient(cmcViewWidth / 2, cmcViewHeigth,
							cmcViewHeigth / 2, Color.argb(255, 0, 0, 0),
							lcs.colorVal, Shader.TileMode.CLAMP);

				} else {
					rd = new RadialGradient(cmcViewWidth / 2, cmcViewHeigth,
							cmcViewWidth / 2, Color.argb(255, 0, 0, 0),
							lcs.colorVal, Shader.TileMode.CLAMP);
				}

				return rd;

			}

		};

		PaintDrawable p = new PaintDrawable();
		p.setShape(new RectShape());
		p.setShaderFactory(sf);
		// p.setCornerRadii(new float[] { 5, 5, 5, 5, 0, 0, 0, 0 });

		layers[0] = (Drawable) p;

		LayerDrawable composite = new LayerDrawable(layers);

		setBackgroundDrawable(composite);

		// new ProfilePictureGrabber().execute(MotherActivity.ufbd.getId());

		// Toast.makeText(context, "Id is " + MotherActivity.ufbd.getId(),
		// Toast.LENGTH_SHORT).show();

	}

	private void loadImage() {
		try {

			File sd = Environment.getExternalStorageDirectory();
			File f = new File(sd, "profilePic.bmp");
			// FileOutputStream out = new
			// FileOutputStream(f);
			// bmp.compress(Bitmap.CompressFormat.PNG,
			// 90, out);
			// out.close();
			bmpUserPic = BitmapFactory.decodeFile(f.toString());

			Log.d("GUFRAN FILE", "IMAGE LOADED");
			// Log.d("GUFRAN FILE", "FILE SAVED");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int calculateDistance(Point a, Point b) {

		return (int) Math.pow(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2),
				0.5);
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {

		currentX = (int) me.getX();
		currentY = (int) me.getY();

		// double xCordPercent = (cmcViewWidth - (currentX / cmcViewWidth)) *
		// 100;
		// double yCordPercent = (cmcViewWidth - (currentY / cmcViewHeigth)) *
		// 100;

		double xCordPercent = currentX;
		double yCordPercent = currentY;

		Toast toast = new Toast(currentContext);

		toast.setDuration(Toast.LENGTH_LONG);

		CMCToast cmcToastView = new CMCToast(currentContext);
		cmcToastView.setMessage("Postion is (" + xCordPercent + ","
				+ yCordPercent + ")");
		cmcToastView.setColor(lcs.colorVal);

		toast.setView(cmcToastView);

		// toast.show();

		// if curentX>=lineFourStartX
		// currentX<=lineOneStartX
		// cmcViewHeigth - (int) (0.35 * cmcViewHeigth)

		double xPercent = ((xCordPercent / cmcViewWidth) * 100);
		double yPercent = ((yCordPercent / cmcViewHeigth) * 100);

		Toast.makeText(currentContext,
				"X% is " + xPercent + " Y%  is " + yPercent, Toast.LENGTH_SHORT)
				.show();

		if ((xPercent > 15) && (xPercent < 50) && (yPercent > 13)
				&& (yPercent < 32)) {
			Intent i2 = new Intent(
					"com.cmcdelhi.cmcdelhiquark.CourseListActivity");
			currentContext.startActivity(i2);
			((Activity) currentContext).overridePendingTransition(
					R.anim.push_left_in, R.anim.push_left_out);
		}

		if ((xPercent > 65) && (xPercent < 100) && (yPercent > 13)
				&& (yPercent < 32)) {
			// Intent i3 = new Intent(android.content.Intent.ACTION_DIAL);
			// i3.setData(Uri.parse("tel:01165905335"));
			// currentContext.startActivity(i3);

			CMCDialogCreator cdc = new CMCDialogCreator(currentContext);
			Dialog d = cdc.generateDialog(1);
			d.show();
		}

		if ((xPercent > 40) && (xPercent < 52) && (yPercent > 57)
				&& (yPercent < 70)) {
			Intent i3 = new Intent(android.content.Intent.ACTION_VIEW);
			i3.setData(Uri
					.parse("https://www.facebook.com/pages/CMC-Ltd-Academy/585364224824373"));
			currentContext.startActivity(i3);
		}

		if ((xPercent > 10) && (xPercent < 30) && (yPercent > 57)
				&& (yPercent < 70)) {
			// Intent i3 = new Intent(android.content.Intent.ACTION_VIEW);
			// i3.setData(Uri.parse("http://www.cmcdelhi.com/"));
			// currentContext.startActivity(i3);

			Intent i3 = new Intent(
					"com.cmcdelhi.cmcdelhiquark.ImageNotesActivity");
			currentContext.startActivity(i3);

		}

		if ((xPercent > 65) && (xPercent < 100) && (yPercent > 42)
				&& (yPercent < 50)) {
			// Intent i3 = new
			// Intent("com.cmcdelhi.cmcdelhiquark.KnowUsActivity");
			// currentContext.startActivity(i3);
			// ((Activity) currentContext).overridePendingTransition(
			// R.anim.push_left_in, R.anim.push_left_out);

			// CMCNotificationGenerator cng = new CMCNotificationGenerator(
			// currentContext);
			//
			// PendingIntent pi = PendingIntent.getActivity(
			// currentContext,
			// 0,
			// new Intent(android.content.Intent.ACTION_VIEW, Uri
			// .parse("http://www.cmcdelhi.com")), 0);
			//
			// cng.generateNotification("Happy Diwali",
			// "CMC Delhi Wishes you a warm and prosperous diwali", pi, 0,
			// true, true, new Date(2013 - 1900, 10, 4, 2, 18));

			Intent i0 = new Intent(
					"com.cmcdelhi.cmcdelhiquark.UtilityActivity");
			currentContext.startActivity(i0);

		}

		if ((xPercent > 88) && (xPercent < 100) && (yPercent > 54)
				&& (yPercent < 100)) {
			Intent i = new Intent("com.cmcdelhi.cmcdelhiquark.FullMenuActivity");
			currentContext.startActivity(i);
			((Activity) currentContext).overridePendingTransition(
					R.anim.push_left_in, R.anim.push_left_out);
		}

		//
		// if ((currentX > 60) && (currentX < 280) && (currentY > 80)
		// && (currentY < 280)) {
		//

		// //

		//

		//
		// // Intent i4 = new
		// Intent("com.cmcdelhi.cmcdelhiquark.EnquiryActivity");
		// // currentContext.startActivity(i4);
		//
		// // Intent i5 = new Intent(
		// // "com.cmcdelhi.cmcdelhiquark.TermsConditionActivity");
		// // currentContext.startActivity(i5);
		//
		// }
		//
		// if (((currentX > lineFourStartX) && (currentX < lineOneStartX))
		// && ((currentY < yMax) && (currentY > yMin))) {
		//
		// Intent i = new Intent(android.content.Intent.ACTION_VIEW,
		// Uri.parse("http://www.cmcdelhi.com"));
		// ((Activity) currentContext).startActivity(i);
		// // Toast.makeText(
		// // getContext(),
		// // " BRowser Touched DOWN at " + me.getX() + "," + me.getY()
		// // + " with pressure " + me.getPressure(),
		// // Toast.LENGTH_SHORT).show();
		//
		// }

		// Toast.makeText(getContext(),
		// " Configs  " + xPercent + " % ," + yPercent + " % ",
		// Toast.LENGTH_SHORT).show();

		Drawable[] layers = new Drawable[1];

		ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
			@Override
			public Shader resize(int width, int height) {
				// LinearGradient lg = new LinearGradient(
				// currentX,
				// currentY,
				// 90,
				// cmcViewHeigth,
				// new int[] {
				// Color.argb(255, 63, 122, 182), // please
				// // input
				// // color
				// // from resource for color-4
				// Color.argb(255, 63, 122, 182),
				// Color.argb(255, 63, 122, 182), Color.WHITE },
				// new float[] { 0, 0.49f, 0.50f, 1 },
				// Shader.TileMode.CLAMP);
				// return lg;

				RadialGradient rd = new RadialGradient(currentX, currentY,
						cmcViewHeigth / 3, Color.argb(255, 0, 0, 0),
						lcs.colorVal, Shader.TileMode.CLAMP);

				return rd;

			}

		};

		PaintDrawable p = new PaintDrawable();
		p.setShape(new RectShape());
		p.setShaderFactory(sf);
		// p.setCornerRadii(new float[] { 5, 5, 5, 5, 0, 0, 0, 0 });

		layers[0] = (Drawable) p;

		LayerDrawable composite = new LayerDrawable(layers);

		setBackgroundDrawable(composite);

		return super.onTouchEvent(me);

	}

	public class InvalidateThread extends Thread {
		public void run() {

		}
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
		// "fonts/Roboto-Thin.ttf");

		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Mathlete-Bulky.otf");

		// starry effect
		// do this only when the background is black

		if (lcs.colorVal == Color.argb(255, 0, 0, 0)) {

			Random rnd = new Random();

			Paint p1 = new Paint();
			p1.setAntiAlias(true);
			p1.setStrokeWidth(3);
			p1.setColor(Color.argb(50, 212, 230, 236));

			canvas.drawText("G " + accX + "," + accY + "," + accZ, 100, 100, p1);

			// /

			for (int i = 0; i < squareCollection.length; i++) {
				// square collection
				int sqVal = (int) (rnd.nextFloat() * 1000);
				squareCollection[i] = sqVal;

			}

			Paint p2 = new Paint();
			p2.setAntiAlias(true);
			p2.setStrokeWidth(3);
			p2.setColor(Color.argb(50, 212, 230, 236));

			for (int i = 0; i < squareCollection.length - 1; i++) {
				canvas.drawRect(new Rect(squareCollection[i] + 4,
						squareCollection[i + 1] + 4, squareCollection[i] + 7,
						squareCollection[i + 1] + 7), p1);
				canvas.drawRect(new Rect(squareCollection[i],
						squareCollection[i + 1], squareCollection[i] + 7,
						squareCollection[i + 1] + 7), p2);
			}

			p1.setAntiAlias(true);
			p1.setStrokeWidth(3);
			p1.setColor(Color.argb(70, 212, 230, 236));

			for (int i = 0; i < starCollection.length; i++) {

				Star s = new Star();
				int xVal = (int) (rnd.nextFloat() * 1000);
				int yVal = (int) (rnd.nextFloat() * 1000);
				s.setP(new Point(xVal, yVal));
				s.setS("G" + i);

				starCollection[i] = s;

			}

			System.out.println("Printing  Details-------");

			for (Star star : starCollection) {
				System.out.println(star.getS() + "-" + star.getP().x + ","
						+ star.getP().y);
			}

			Paint p3 = new Paint();
			p3.setAntiAlias(true);
			p3.setStrokeWidth(1);
			p3.setColor(Color.argb(180, 212, 230, 236));

			for (int i = 0; i < starCollection.length - 1; i++) {
				Star starA = starCollection[i];
				int minDistance = calculateDistance(starCollection[i].getP(),
						starCollection[i + 1].getP());

				System.out.println("Mini Distance " + minDistance);

				for (int j = 0; j < starCollection.length; j++) {
					Star starB = starCollection[j];

					int distance = calculateDistance(starA.getP(), starB.getP());

					System.out.println("Dis between " + starA.getS() + " & "
							+ starB.getS() + " is " + distance);

					if (distance != 0) {
						if (distance - minDistance < 50) {
							minDistance = distance;
						}
					}

				}

				System.out.println("Verified Min Distance " + minDistance);

				System.out.println("----");

				// again iterating to the min dist element
				for (int k = 0; k < starCollection.length; k++) {
					Star starK = starCollection[k];

					if (calculateDistance(starA.getP(), starK.getP()) == minDistance) {
						// join the points
						System.out.println("Joined " + starA.getS() + " - "
								+ starK.getS());

						canvas.drawRect(
								new Rect(starA.getP().x, starA.getP().y, starA
										.getP().x + 3, starA.getP().y + 3), p3);

						canvas.drawLine(starA.getP().x, starA.getP().y,
								starK.getP().x, starK.getP().y, p1);

					}

				}

			}

		}

		// drawing icins lines othee stuffs

		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setAlpha(100);
		p.setColor(Color.WHITE);
		// p.setStrokeWidth(5.0f);

		// if color is black then don draw the lines
		if (lcs.colorVal != Color.argb(255, 0, 0, 0)) {

			// canvas.drawLine(cmcViewWidth-300
			// ,cmcViewHeigth-500,cmcViewWidth-300,
			// cmcViewHeigth-100, p);
			canvas.drawLine(lineOneStartX, lineOneStartY, lineOneEndX,
					lineOneEndY, p);
			canvas.drawLine(lineTwoStartX, lineTwoStartY, lineTwoEndX,
					lineTwoEndY, p);
			canvas.drawLine(lineThreeStartX, lineThreeStartY, lineThreeEndX,
					lineThreeEndY, p);
			canvas.drawLine(lineFourStartX, lineFourStartY, lineFourEndX,
					lineFourEndY, p);

			// canvas.drawLine(currentX, currentY, currentX
			// + (lineFourEndX - lineFourStartX), currentY
			// + (lineFourEndY - lineFourStartY), p);

			canvas.drawLine(lineFiveStartX, lineFiveStartY, lineFiveEndX,
					lineFiveEndY, p);

			canvas.drawLine(lineSixStartX, lineSixStartY, lineSixEndX,
					lineSixEndY, p);

		}

		Paint pCMCHeaderRect = new Paint();
		pCMCHeaderRect.setAntiAlias(true);
		pCMCHeaderRect.setAlpha(100);
		pCMCHeaderRect.setColor(Color.WHITE);

		// canvas.drawRect(cmcHeaderRect, pCMCHeaderRect);

		// canvas.drawRect(enquiriesRect, p);

		// canvas.drawRect(tataEnterprise, p);

		if (cmcViewHeigth > cmcViewWidth) {
			if (MotherActivity.ufbd.getName() != null) {
				canvas.drawBitmap(bmpUserPic, cmcViewWidth
						- ((int) (0.15 * cmcViewWidth)), 20, p);
			}

			canvas.drawBitmap(courseScaled, (int) (lineTwoStartX + 5),
					lineOneStartY + 20, p);

			canvas.drawBitmap(facebookScaled, cmcViewWidth
					- (int) (0.62 * cmcViewWidth),
					(int) (0.60 * cmcViewHeigth), p);

			canvas.drawBitmap(browserScaled, cmcViewWidth
					- (int) (0.85 * cmcViewWidth),
					(int) (0.60 * cmcViewHeigth), p);

			canvas.drawBitmap(studentCallScaled,
					(int) ((lineOneStartX + cmcViewWidth) / 2) - 40,
					(int) ((lineFiveStartY - lineTwoStartY) / 2), p);

			canvas.drawBitmap(mapScaled, cmcViewWidth
					- (int) (0.64 * cmcViewWidth),
					(int) (0.40 * cmcViewHeigth), p);

			canvas.drawBitmap(mailScaled, cmcViewWidth
					- (int) (0.90 * cmcViewWidth),
					(int) (0.40 * cmcViewHeigth), p);

			// canvas.drawBitmap(cmcLogoScaled, (int) (0.03 * cmcViewWidth),
			// (int) (0.04 * cmcViewHeigth), p);

			// drawing texts

			Paint pKnowUs = new Paint();
			pKnowUs.setTextSize(60.0f);
			pKnowUs.setColor(Color.WHITE);
			pKnowUs.setAntiAlias(true);
			pKnowUs.setTypeface(tf);

			canvas.drawText("Know Us", cmcViewWidth
					- (int) (0.35 * cmcViewWidth),
					(int) ((lineFiveStartY + lineSixStartY) / 2) + 10, pKnowUs);

			// user name

			Paint pUsername = new Paint();
			pUsername.setTextSize(55.0f);
			pUsername.setColor(Color.WHITE);
			pUsername.setAntiAlias(true);
			pUsername.setTypeface(tf);

			if (MotherActivity.ufbd.getName() != null) {
				canvas.drawText("Hi  " + MotherActivity.ufbd.getName(),
						(int) (0.07 * cmcViewWidth),
						(int) (0.07 * cmcViewHeigth), pUsername);
			} else {
				canvas.drawText("Welcome ", (int) (0.07 * cmcViewWidth),
						(int) (0.07 * cmcViewHeigth), pUsername);
			}

			Paint pUtities = new Paint();
			pUtities.setTextSize(55.0f);
			pUtities.setColor(Color.WHITE);
			pUtities.setAntiAlias(true);
			pUtities.setTypeface(tf);

			canvas.rotate(270, cmcViewWidth - (int) (0.24 * cmcViewWidth),
					(int) (0.75 * cmcViewHeigth));

			canvas.drawText("Utilities", cmcViewWidth
					- (int) (0.24 * cmcViewWidth),
					(int) (0.75 * cmcViewHeigth), pUtities);

			canvas.rotate(-270, cmcViewWidth - (int) (0.24 * cmcViewWidth),
					(int) (0.75 * cmcViewHeigth));

			canvas.rotate(270, cmcViewWidth - (int) (0.04 * cmcViewWidth),
					(int) (0.75 * cmcViewHeigth));

			canvas.drawText("More", cmcViewWidth - (int) (0.04 * cmcViewWidth),
					(int) (0.75 * cmcViewHeigth), pUtities);

			canvas.rotate(-270, cmcViewWidth - (int) (0.04 * cmcViewWidth),
					(int) (0.75 * cmcViewHeigth));

			Paint pCourses = new Paint();
			pCourses.setTextSize(60.0f);
			pCourses.setColor(Color.WHITE);
			pCourses.setAntiAlias(true);
			pCourses.setTypeface(tf);

			canvas.rotate(270, lineOneStartX - 10, lineTwoStartY + 200);
			canvas.drawText("Courses", lineOneStartX - 10, lineTwoStartY + 190,
					pCourses);

		} else {
			// For Landscape MODE
			canvas.drawBitmap(courseScaled, lineOneStartX - 200,
					lineOneStartY + 10, p);

			// user image

			// canvas.drawBitmap(courseScaled, cmcViewWidth - 100, 10, p);

			// canvas.drawBitmap(facebookScaled,
			// (int) ((lineFourStartX + lineOneStartX) / 2) - 40,
			// cmcViewHeigth - (int) (0.15 * cmcViewHeigth), p);
			// canvas.drawBitmap(browserScaled,
			// (int) ((lineFourStartX + lineOneStartX) / 2) - 50,
			// cmcViewHeigth - (int) (0.35 * cmcViewHeigth), p);

			canvas.drawBitmap(studentCallScaled,
					(int) ((lineOneStartX + cmcViewWidth) / 2) - 70,
					(int) ((lineTwoStartY + lineFiveStartY) / 2) - 80, p);

			canvas.drawBitmap(facebookScaled, cmcViewWidth
					- (int) (0.50 * cmcViewWidth),
					(int) (0.70 * cmcViewHeigth), p);

			canvas.drawBitmap(browserScaled, cmcViewWidth
					- (int) (0.62 * cmcViewWidth),
					(int) (0.70 * cmcViewHeigth), p);

			canvas.drawBitmap(mapScaled, cmcViewWidth
					- (int) (0.80 * cmcViewWidth),
					(int) (0.66 * cmcViewHeigth), p);

			canvas.drawBitmap(mailScaled, cmcViewWidth
					- (int) (0.95 * cmcViewWidth),
					(int) (0.66 * cmcViewHeigth), p);

			// cmcLogoScaled = Bitmap.createScaledBitmap(cmcLogo, 120, 75,
			// true);
			//
			// canvas.drawBitmap(cmcLogoScaled, (int) (0.03 * cmcViewWidth),
			// (int) (0.04 * cmcViewHeigth), p);

			// drwaing texts

			Paint pKnowUs = new Paint();
			pKnowUs.setTextSize(37.0f);
			pKnowUs.setColor(Color.WHITE);
			pKnowUs.setAntiAlias(true);
			pKnowUs.setTypeface(tf);

			canvas.drawText("Know Us", cmcViewWidth
					- (int) (0.20 * cmcViewWidth),
					(int) ((lineFiveStartY + lineSixStartY) / 2) + 10, pKnowUs);

			Paint pUtities = new Paint();
			pUtities.setTextSize(35.0f);
			pUtities.setColor(Color.WHITE);
			pUtities.setAntiAlias(true);
			pUtities.setTypeface(tf);

			canvas.drawText("Utilities", cmcViewWidth
					- (int) (0.35 * cmcViewWidth), cmcViewHeigth
					- (int) (0.08 * cmcViewHeigth), pUtities);

			canvas.drawText("More", cmcViewWidth - (int) (0.10 * cmcViewWidth),
					cmcViewHeigth - (int) (0.08 * cmcViewHeigth), pUtities);

			Paint pCourses = new Paint();
			pCourses.setTextSize(50.0f);
			pCourses.setColor(Color.WHITE);
			pCourses.setAntiAlias(true);
			pCourses.setTypeface(tf);

			canvas.drawText("Courses", lineTwoStartX + 5, lineTwoStartY + 50,
					pCourses);

		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		accXPrev = accX;
		accYPrev = accY;
		accZPrev = accZ;

		accX = event.values[0];
		accY = event.values[1];
		accZ = event.values[2];

		if ((accX - accXPrev > 1) || (accZ - accZPrev > 1)) {
			invalidate();
		}

		// invalidate();
	}

}
