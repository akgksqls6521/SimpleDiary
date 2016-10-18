package kr.hs.emirim.qls6521.simplediary;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

        butSave.setOnClickListener(new View.OnClickListener() {//이벤트 처리자!-익명클래스를 사용해 핸들러 구현
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream out=openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String diaryContents=editDiary.getText().toString();
                    out.write(diaryContents.getBytes());//byte 자료형으로 바꿔주기
                    out.close();
                    Toast.makeText(getApplicationContext(),"저장이 완료되었습니다.",Toast.LENGTH_SHORT).show();//toast객체 반환받기? show!
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    String readDiary(String fileName){
        String diaryContents=null;
        try {
            FileInputStream in=openFileInput(fileName);
            byte[] txt=new byte[500];//500바이트 읽어오겠어!
            in.read(txt);
            in.close();
            diaryContents=new String(txt);
            butSave.setText("수정하기");
        } catch (IOException e) {
            editDiary.setHint("읽어올 일기가 없음");
            butSave.setText("새로 저장");
        }

        return diaryContents;
    }
}
