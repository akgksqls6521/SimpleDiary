package kr.hs.emirim.qls6521.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datePic;
    EditText editDiary;
    Button butSave;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePic=(DatePicker)findViewById(R.id.date_picker);
        editDiary=(EditText)findViewById(R.id.edit_content);
        butSave=(Button)findViewById(R.id.but_save);

        Calendar calendar=Calendar.getInstance();
        int nowYear=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int date=calendar.get(Calendar.DATE);

        //DatePicker에 현재 날짜 설정
        datePic.init(nowYear,month,date,new DatePicker.OnDateChangedListener(){

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName=year+"_"+(monthOfYear+1)+"_"+dayOfMonth+".txt";
                String content=readDiary(fileName);
                editDiary.setText(content);
                butSave.setEnabled(true);
            }
        });
    }

    public String readDiary(String fileName){
        return null;
    }
}
