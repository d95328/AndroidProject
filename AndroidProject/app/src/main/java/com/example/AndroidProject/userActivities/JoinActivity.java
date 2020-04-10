package com.example.AndroidProject.userActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AndroidProject.MainActivity;
import com.example.AndroidProject.R;
import com.example.AndroidProject.ayncTask.DoubleCheckAsyncTask;
import com.example.AndroidProject.ayncTask.RequestAsyncTask;
import com.example.AndroidProject.dto.MemberDTO;

import java.sql.Date;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    Button signBtn, cancelBtn, termsBtn, doubleIdCheck, doubleEmailCheck;
    EditText joinUserid, joinUserpw, joinUserpwCheck, joinNickname, joinEmail;
    TextView gender;
    String yy,mm,dd;
    String interest;
    DatePicker datePicker;
    Spinner joinInterest;
    RadioGroup joinGenderGroup;
    CheckBox joinAccept;
    boolean idCheck;
    boolean emailCheck;

    //유효성검사 인라인뷰
    TextView reg_id, reg_pw, reg_pwCheck, reg_email, reg_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        setTitle("JOIN");

        signBtn = findViewById(R.id.sign_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        termsBtn = findViewById(R.id.terms_btn);

        joinUserid = findViewById(R.id.join_userId);
        joinUserpw = findViewById(R.id.join_userPw);
        joinUserpwCheck = findViewById(R.id.join_userPwdCheck);
        joinNickname = findViewById(R.id.join_nickname);
        joinEmail = findViewById(R.id.join_email);
        joinInterest = findViewById(R.id.join_interest);
        joinAccept = findViewById(R.id.join_accept);
        datePicker = findViewById(R.id.join_birth);
        final TextView dateText = findViewById(R.id.join_birthText);
        joinGenderGroup = findViewById(R.id.join_gender);
        gender = findViewById(R.id.gender);

//        //버튼 이용 아이디 중복검사
//        doubleIdCheck = findViewById(R.id.doubleIdCheck);
//        doubleIdCheck.setOnClickListener(new View.OnClickListener() {
//            SharedPreferences sharedPreferences = getSharedPreferences("doubleCheck", MODE_PRIVATE);
////            SharedPreferences.Editor
//            @Override
//            public void onClick(View v) {
//                if(idReg() == true) {
//                    MemberDTO idcheckDTO = new MemberDTO();
//                    idcheckDTO.setM_userid(joinUserid.getText().toString());
//                    DoubleCheckAsyncTask idCheckAsync = new DoubleCheckAsyncTask(JoinActivity.this, idcheckDTO);
//                    idCheckAsync.execute();
//                    idCheck = true;
//                } else if (idReg() == false) {
//                    alert("형식에 맞는 아이디를 입력후 눌러주세요.");
//                    return;
//                }
//            }
//        });

        //버튼이용 이메일 중복검사
//        doubleEmailCheck = findViewById(R.id.doubleEmailCheck);
//        doubleEmailCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(emailReg() == true) {
//                    MemberDTO emailcheckDTO = new MemberDTO();
//                    emailcheckDTO.setM_email(joinEmail.getText().toString());
//                    DoubleCheckAsyncTask emailCheckAsync = new DoubleCheckAsyncTask(JoinActivity.this, emailcheckDTO);
//                    emailCheckAsync.execute();
//                    emailCheck = true;
//                } else if (emailReg() == false) {
//                    alert("형식에 맞는 이메일을 입력후 눌러주세요.");
//                    return;
//                }
//            }
//        });


        //약관 다이얼로그 띄우기

       //유효성검사 인라인뷰
        reg_id = findViewById(R.id.id_reg);
        reg_pw = findViewById(R.id.pw_reg);
        reg_pwCheck = findViewById(R.id.pwCheck_reg);
        reg_email = findViewById(R.id.email_reg);
        reg_nickname = findViewById(R.id.nickname_reg);


        //datepicker에 담겨온 생년월일 DATE 타입(int로 들어옴) 값을 String으로 변환
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                yy=Integer.toString(year);
                mm=Integer.toString(monthOfYear+1);
                dd=Integer.toString(dayOfMonth);
                dateText.setText(yy+"-"+mm+"-"+dd);
            }
        });
        //성별 체크 남,여 값 가져오기
        joinGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.man) {
                    gender.setText("남");
                } else if(checkedId == R.id.woman) {
                    gender.setText("여");
                }
            }
        });
        //관심사 Spinner 값 가져오기
        interest = joinInterest.getSelectedItem().toString();

        //값입력시 인라인뷰 출력
        joinUserid.setOnKeyListener(new onKeyUp());
        joinUserpw.setOnKeyListener(new onKeyUp());
        joinNickname.setOnKeyListener( new onKeyUp() );
        joinUserpwCheck.setOnKeyListener(new onKeyUp());
        joinEmail.setOnKeyListener(new onKeyUp());

        //회원가입 버튼 눌렀을때 이벤트 리스너
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //공백여부처리
                if(joinUserid.getText().toString().length() == 0) {
                    alert("아이디를 입력하세요.");
                    joinUserid.requestFocus();
                    return;
                }
                if(joinUserpw.getText().toString().length() == 0 && joinUserpwCheck.getText().toString().length() == 0) {
                    alert("비밀번호를 입력하세요.");
                    joinUserpw.requestFocus();
                    return;
                }
                if(joinNickname.getText().toString().length() == 0) {
                    alert("닉네임을 입력하세요.");
                    joinNickname.requestFocus();
                    return;
                }
                if(joinEmail.getText().toString().length() == 0) {
                    alert("이메일을 입력하세요.");
                    joinEmail.requestFocus();
                    return;
                }
                //개인정보수집동의체크
                if(!joinAccept.isChecked()) {
                    alert("개인정보 수집에 동의해주세요.");
                    return;
                }

                MemberDTO joinDto = new MemberDTO();
                joinDto.setM_userid(joinUserid.getText().toString());
                joinDto.setM_userpw(joinUserpw.getText().toString());
                joinDto.setM_nickname(joinNickname.getText().toString());
                joinDto.setM_email(joinEmail.getText().toString());
                joinDto.setM_birth(dateText.getText().toString());
                joinDto.setM_gender(gender.getText().toString());
                joinDto.setM_interest(interest);
                RequestAsyncTask joinRequestAsync = new RequestAsyncTask(JoinActivity.this, joinDto);
                joinRequestAsync.execute();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("회원가입을 취소하셨습니다.");
                finish();
            }
        });


    }//end of onCreate


    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //아이디 유효성검사
    synchronized private boolean idReg(){
        if( !Pattern.matches("^[a-z]+[a-z0-9]{6,16}$", joinUserid.getText().toString()) ) {
            //영소문자로 시작 영+숫자 6~16글자
            reg_id.setText("유효하지않는형식(영+숫자6~16자)");
            reg_id.setTextColor(Color.RED);
            return false;
        }
        return true;
    }
    //아이디 중복검사
    synchronized public boolean CheckId() throws ExecutionException, InterruptedException {
        MemberDTO idcheckDTO = new MemberDTO();
        idcheckDTO.setM_userid(joinUserid.getText().toString());
        DoubleCheckAsyncTask idCheckAsync = new DoubleCheckAsyncTask(JoinActivity.this, idcheckDTO);
        String result = idCheckAsync.execute().get();
        if (result.equals("success")) {
            return true;
        }
        return false;
    }

    //비밀번호 유효성검사
    private boolean pwReg() {
        if( !Pattern.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", joinUserpw.getText().toString()) ) {
            //영어로 시작 영+숫자+특수문자 8~16자리
            reg_pw.setText("유효하지않은형식(영+숫자+특문 8~16자)");
            reg_pw.setTextColor(Color.RED);
            return false;
        } else if( Pattern.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", joinUserpw.getText().toString()) ) {
            reg_pw.setText("사용가능한 비밀번호입니다.");
            reg_pw.setTextColor(Color.GREEN);
            return true;
        }
        return true;
    }
    private boolean pwEqualsReg() {
        if( !joinUserpw.getText().toString().equals(joinUserpwCheck.getText().toString()) ) {
            reg_pwCheck.setText("비밀번호 불일치");
            reg_pwCheck.setTextColor(Color.RED);
            return false;
        } else if ( joinUserpw.getText().toString().equals(joinUserpwCheck.getText().toString()) ) {
            reg_pwCheck.setText("비밀번호가 일치합니다.");
            reg_pwCheck.setTextColor(Color.GREEN);
            return true;
        }
        return true;
    }

    //닉네임 유효성검사
    synchronized private boolean nickNameReg() {
        if( !Pattern.matches("^[가-힣]{2,6}$", joinNickname.getText().toString()) ) {
            //한글만 2~6글자
            reg_nickname.setText("유효하지않은형식(한2-6자)");
            reg_nickname.setTextColor(Color.RED);
            return false;
        }
        return true;
    }
    //닉네임 중복검사
    synchronized public boolean CheckNickName() throws ExecutionException, InterruptedException {
        MemberDTO nickNameDTO = new MemberDTO();
        nickNameDTO.setM_nickname(joinNickname.getText().toString());
        DoubleCheckAsyncTask checkAsyncTask = new DoubleCheckAsyncTask(JoinActivity.this, nickNameDTO);
        String result = checkAsyncTask.execute().get();
        if(result.equals("success")){
            return true;
        }
        return false;
    }

    //이메일 유효성검사
    synchronized private boolean emailReg() {
        if( !Pattern.matches("^[a-zA-z0-9]{6,9}@[a-z]{1,6}.[a-z]{3}$", joinEmail.getText().toString()) ) {
            reg_email.setText("유효하지않은 이메일형식");
            reg_email.setTextColor(Color.RED);
            return false;
        }
        return true;
    }
    //이메일 중복검사
    synchronized public boolean CheckEmail() throws ExecutionException, InterruptedException {
        MemberDTO emailcheckDTO = new MemberDTO();
        emailcheckDTO.setM_email(joinEmail.getText().toString());
        DoubleCheckAsyncTask emailCheckAsync = new DoubleCheckAsyncTask(JoinActivity.this, emailcheckDTO);
        String result = emailCheckAsync.execute().get();
        if(result.equals("success")) {
            return true;
        }
        return false;
    }

    //입력시 인라인뷰 출력위한 이너클래스 선언
    private class onKeyUp implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if(v.getId() == R.id.join_userId) {
                if (!idReg()) {
                    return false;
                } else if(idReg()) {
                    try {
                        if( !CheckId() ) {
                            reg_id.setText("이미사용중인 아이디입니다.");
                            reg_id.setTextColor(Color.RED);
                        } else {
                            reg_id.setText("사용가능한 아이디입니다.");
                            reg_id.setTextColor(Color.GREEN);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(v.getId() == R.id.join_userPw) {
                if (!pwReg()) {
                    return false;
                }
            }
            if(v.getId() == R.id.join_userPwdCheck) {
                if (!pwEqualsReg()) {
                    return false;
                }
            }
            if(v.getId() == R.id.join_nickname) {
                if (!nickNameReg()) {
                    return false;
                } else if (nickNameReg()){
                    try {
                        if( !CheckNickName() ) {
                            reg_nickname.setText("이미사용중인 닉네임입니다.");
                            reg_nickname.setTextColor(Color.RED);
                            return false;
                        } else {
                            reg_nickname.setText("사용가능한 닉네임입니다.");
                            reg_nickname.setTextColor(Color.GREEN);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(v.getId() == R.id.join_email) {
                if( !emailReg()) {
                    return false;
                } else if( emailReg() ){
                    try {
                        if( !CheckEmail() ) {
                            reg_email.setText("이미사용중인 이메일입니다.");
                            reg_email.setTextColor(Color.RED);
                            return false;
                        }else {
                            reg_email.setText("사용가능한 이메일입니다.");
                            reg_email.setTextColor(Color.GREEN);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            return false;
        }
    }


}//end of class
