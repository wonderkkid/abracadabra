package com.justdiary;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

public class NoteEdit extends Activity {
	
    public static int numTitle = 1; 
    public static String curDate = "";
    public static String curText = ""; 
    private EditText mTitleText;
    private EditText mBodyText;
    private TextView mDateText;
    private Long mRowId;

    private Cursor note;
    
    public static class LineEditText extends EditText{
    	// we need this constructor for LayoutInflater
    	
    	public LineEditText(Context context, AttributeSet attrs) {
    		super(context, attrs);
    		
    		mRect = new Rect();
    		mPaint = new Paint();
	        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	        mPaint.setColor(Color.BLUE);
    	}
    	
    	private Rect mRect;
    	private Paint mPaint;
    	
    	@Override
    	protected void onDraw(Canvas canvas) {
    		
    		int height = getHeight();
    		int line_height = getLineHeight();

            int count = height / line_height;
            
            if (getLineCount() > count)
            	count = getLineCount();

            Rect r = mRect;
            Paint paint = mPaint;
            
            int baseline = getLineBounds(0, r);

            for (int i = 0; i < count; i++) {
            	canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
            	baseline += getLineHeight();
            	
            	super.onDraw(canvas);
            }
    	}
    }
}