package com.cmcdelhi.cmcdelhiquark;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ExvView extends View {

	Context context;

	public ExvView(Context context) {
		super(context);
		setBackgroundColor(Color.GREEN);
		this.context = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Paint p = new Paint();
		p.setAntiAlias(true);

		canvas.drawText("Hell Friend", 100, 200, p);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		Toast.makeText(context,
				"Toched at " + event.getX() + " , " + event.getY(),
				Toast.LENGTH_SHORT).show();

		return super.onTouchEvent(event);
	}
}
