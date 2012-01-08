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

//开始按钮
Button iatButton = (Button) findViewById(R.id.button4);
Log.d(TAG, "[onCreate]" + "find button done");

Log.d(TAG, "[onCreate]" + iatButton.toString());
iatButton.setOnClickListener(listener1);

Log.d(TAG, "[onCreate]" + "set click listener done");

//结果
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
String s2="温州市。";
String s3="证书编号3303。";
String s4="证书编号3605。";
String s5="浙江省。";

if(s1.equals(s2)){
 mAnswerText.setText("签发机构：温州市龙湾区人民政府\n签发时间：2005.4.1\n海域使用权人:温州市状元海洋捕捞大队\n法人姓名：夏美兄\n项目名称：《温州状元渔业大队杂货码头工程》");
 imageView1.setBackgroundResource(R.drawable.longwan);}
else if(s1.equals(s3)){
 mAnswerText.setText("签发机构：浙江省平阳县人民政府\n签发时间：2008.12.15 \n海域使用权人:平阳县利得海涂围垦开发有限公司\n法人姓名：章雷\n项目名称：《平阳县利得海涂围垦开发有限公司养殖》");
 imageView1.setBackgroundResource(R.drawable.pingyangxian);}
else if(s1.equals(s4)){
 mAnswerText.setText("签发机构：浙江省苍南县人民政府\n签发时间：2003.5.23 \n海域使用权人:苍南县运输公司\n法人姓名：姜祥庆\n项目名称：《苍南县运输公司镇前路简易码头》");
 imageView1.setBackgroundResource(R.drawable.cangnanxian);}
else if(s1.equals(s5)){
 mAnswerText.setText("签发机构：浙浙江省乐清市人民政府\n签发时间：2001.11.14\n海域使用权人:乐清市翁垟繁兴育苗场\n法人姓名：林赞成；\n项目名称：《翁垟繁兴育苗场》");
 imageView1.setBackgroundResource(R.drawable.leqingshi);}

else{mAnswerText.setText("对不起，没有查到您需要的信息");imageView1.setBackgroundResource(R.drawable.cry);}

}}

// mResultText.append(builder);
// mResultText.setSelection(mResultText.length());



      

public void onEnd(SpeechError arg0) {
// TODO Auto-generated method stub
}
}