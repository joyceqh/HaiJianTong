package com.esri.arcgis.android.samples.addlayer;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechConfig.RATE;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;


public class Search extends Activity  implements OnClickListener , RecognizerDialogListener 
{

private static final String TAG = "MY Project";

private TextView mCategoryText;
private EditText mResultText;
private TextView mShowText;
private TextView mAnswerText;
private ImageView imageView1;
//private SharedPreferences mSharedPreferences;
RecognizerDialog iatDialog;

OnClickListener listener1 = null;


@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

Log.d(TAG, "[onCreate]" + savedInstanceState);

setContentView(R.layout.search);

((TextView) findViewById(android.R.id.title))
.setGravity(Gravity.CENTER);

listener1 = new OnClickListener() {
     public void onClick(View v) {
     showIatDialog(); 
     
     }
    };

//��ʼ��ť
Button iatButton = (Button) findViewById(R.id.button4);
Log.d(TAG, "[onCreate]" + "find button done");

Log.d(TAG, "[onCreate]" + iatButton.toString());
iatButton.setOnClickListener(listener1);

Log.d(TAG, "[onCreate]" + "set click listener done");

//���
//mResultText = (EditText) findViewById(R.id.result);


iatDialog = new RecognizerDialog(this, "appid=" + getString(R.string.app_id));
iatDialog.setListener(this);


mShowText=(TextView) findViewById(R.id.show);

mAnswerText=(TextView) findViewById(R.id.answer);

imageView1=(ImageView) findViewById(R.id.imageView1);


}




public void onClick(View v) { 
showIatDialog(); 

}



public void showIatDialog()
{
iatDialog.setEngine("sms", null, null); 
iatDialog.setSampleRate(RATE.rate8k); 
//mResultText.setText(null); 
iatDialog.show();
}





public void onResults(ArrayList<RecognizerResult> results,boolean isLast) {
StringBuilder builder = new StringBuilder();
for (RecognizerResult recognizerResult : results) {
builder.append(recognizerResult.text);
mShowText.setText(recognizerResult.text);


String s1=recognizerResult.text;
String s2="�����С�";
String s3="֤����3303��";
String s4="֤����3605��";
String s5="�㽭ʡ��";

if(s1.equals(s2)){
 mAnswerText.setText("ǩ����������������������������\nǩ��ʱ�䣺2005.4.1\n����ʹ��Ȩ��:������״Ԫ�����̴��\n����������������\n��Ŀ���ƣ�������״Ԫ��ҵ����ӻ���ͷ���̡�");
 imageView1.setBackgroundResource(R.drawable.longwan);}
else if(s1.equals(s3)){
 mAnswerText.setText("ǩ���������㽭ʡƽ������������\nǩ��ʱ�䣺2008.12.15 \n����ʹ��Ȩ��:ƽ�������ú�ͿΧ�ѿ������޹�˾\n��������������\n��Ŀ���ƣ���ƽ�������ú�ͿΧ�ѿ������޹�˾��ֳ��");
 imageView1.setBackgroundResource(R.drawable.pingyangxian);}
else if(s1.equals(s4)){
 mAnswerText.setText("ǩ���������㽭ʡ��������������\nǩ��ʱ�䣺2003.5.23 \n����ʹ��Ȩ��:���������乫˾\n����������������\n��Ŀ���ƣ������������乫˾��ǰ·������ͷ��");
 imageView1.setBackgroundResource(R.drawable.cangnanxian);}
else if(s1.equals(s5)){
 mAnswerText.setText("ǩ�����������㽭ʡ��������������\nǩ��ʱ�䣺2001.11.14\n����ʹ��Ȩ��:�������̈��������糡\n�������������޳ɣ�\n��Ŀ���ƣ����̈��������糡��");
 imageView1.setBackgroundResource(R.drawable.leqingshi);}

else{mAnswerText.setText("�Բ���û�в鵽����Ҫ����Ϣ");imageView1.setBackgroundResource(R.drawable.cry);}

}}

// mResultText.append(builder);
// mResultText.setSelection(mResultText.length());



      

public void onEnd(SpeechError arg0) {
// TODO Auto-generated method stub
}
}