package com.cmcdelhi.cmcdelhiquark;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class CMCToast extends View {

	String message;
	int width;
	int height;
	
	int fontColor;

	public CMCToast(Context context) {
		super(context);
		setBackgroundColor(Color.TRANSPARENT);

		message = "";
		height =50;
		width = 10;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint mPaint2 = new Paint();
		mPaint2.setColor(fontColor);
		mPaint2.setTextSize(20.0f);

		mPaint2.setAntiAlias(true);

		// canvas.drawLine(0, 0, 100, 100, mPaint2);

		canvas.drawRoundRect(new RectF(2, 2, width-2, height-2), 7, 7, mPaint2);

		Paint mPaint3 = new Paint();
		mPaint3.setColor(Color.WHITE);

		mPaint3.setAntiAlias(true);

		canvas.drawRoundRect(new RectF(4, 4, width-4, height-4), 7, 7, mPaint3);

		canvas.drawText(message, 18, 32, mPaint2);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(width, height);

	}

	public void setMessage(String message) {
		this.message = message;
		this.width=message.length()*12;
	}
	
	public void setColor(int fontColor) {
		this.fontColor = fontColor;
	}
	
	
}
