package cm.cmcdelhi.dialog;

import com.cmcdelhi.cmcdelhiquark.LockedColorSingleton;
import com.cmcdelhi.cmcdelhiquark.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CMCDialogCreator {
	Context context;

	LinearLayout dialogBackLayout, calldialogBackLayout;
	Typeface tf;
	TextView dialogeTitle, positiveTitile, negativeTitle;
	TextView number1, number2, number3;
	RelativeLayout phone1rellayout, phone2rellayout, phone3rellayout;

	public CMCDialogCreator(Context context) {
		super();
		this.context = context;
	}

	public Dialog generateDialog(int type) {
		switch (type) {
		case 0:
			AlertDialog.Builder builder = new AlertDialog.Builder(
					(Activity) context);
			// builder.setIcon(R.drawable.ic_launcher);

			// builder.setTitle("Login CRedentials");

			LayoutInflater inflater = ((Activity) context).getLayoutInflater();

			View v = inflater.inflate(R.layout.dialoglayout, null);

			builder.setView(v);

			// ///////////////////////////

			dialogBackLayout = (LinearLayout) v
					.findViewById(R.id.dialogBackLayout);

			// dialogBackLayout.setBackgroundColor(LockedColorSingleton
			// .getInstance().colorVal);

			tf = Typeface.createFromAsset(context.getAssets(),
					"fonts/Mathlete-Bulky.otf");

			dialogeTitle = (TextView) v.findViewById(R.id.dialogeTitle);
			positiveTitile = (TextView) v.findViewById(R.id.positiveTitile);
			negativeTitle = (TextView) v.findViewById(R.id.negativeTitle);

			dialogeTitle
					.setTextColor(LockedColorSingleton.getInstance().colorVal);
			dialogeTitle.setTypeface(tf);
			dialogeTitle.setTextSize(50f);

			positiveTitile
					.setTextColor(LockedColorSingleton.getInstance().colorVal);
			positiveTitile.setTypeface(tf);
			positiveTitile.setTextSize(40f);

			negativeTitle
					.setTextColor(LockedColorSingleton.getInstance().colorVal);
			negativeTitle.setTypeface(tf);
			negativeTitle.setTextSize(40f);

			// /////////////

			// builder.setMessage("This is to inform all the student athat the class will be suspended for 10 days");
			return builder.create();
		case 1:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(
					(Activity) context);
			// builder.setIcon(R.drawable.ic_launcher);

			// builder.setTitle("Login CRedentials");

			LayoutInflater inflater2 = ((Activity) context).getLayoutInflater();

			View v2 = inflater2.inflate(R.layout.calldialog_layout, null);

			builder2.setView(v2);

			// ///////////////////////////

			calldialogBackLayout = (LinearLayout) v2
					.findViewById(R.id.calldialogBackLayout);

			// dialogBackLayout.setBackgroundColor(LockedColorSingleton
			// .getInstance().colorVal);

			tf = Typeface.createFromAsset(context.getAssets(),
					"fonts/Mathlete-Bulky.otf");

			number1 = (TextView) v2.findViewById(R.id.number1);
			number2 = (TextView) v2.findViewById(R.id.number2);
			number3 = (TextView) v2.findViewById(R.id.number3);

			phone1rellayout = (RelativeLayout) v2
					.findViewById(R.id.phone1rellayout);
			phone2rellayout = (RelativeLayout) v2
					.findViewById(R.id.phone2rellayout);
			phone3rellayout = (RelativeLayout) v2
					.findViewById(R.id.phone3rellayout);

			number1.setTextColor(LockedColorSingleton.getInstance().colorVal);
			number1.setTypeface(tf);
			number1.setTextSize(50f);
			phone1rellayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Intent i3 = new Intent(android.content.Intent.ACTION_CALL);
					i3.setData(Uri.parse("tel:01165905335"));
					context.startActivity(i3);

				}
			});

			number2.setTextColor(LockedColorSingleton.getInstance().colorVal);
			number2.setTypeface(tf);
			number2.setTextSize(50f);
			phone2rellayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Intent i3 = new Intent(android.content.Intent.ACTION_CALL);
					i3.setData(Uri.parse("tel:01165655335"));
					context.startActivity(i3);

				}
			});

			number3.setTextColor(LockedColorSingleton.getInstance().colorVal);
			number3.setTypeface(tf);
			number3.setTextSize(50f);
			phone3rellayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Intent i3 = new Intent(android.content.Intent.ACTION_CALL);
					i3.setData(Uri.parse("tel:09810324822"));
					context.startActivity(i3);

				}
			});

			// /////////////

			// builder.setMessage("This is to inform all the student athat the class will be suspended for 10 days");
			return builder2.create();

		}
		return null;

	}
}
