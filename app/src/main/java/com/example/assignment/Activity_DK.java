//package com.example.assignment;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.EditText;
//
//public class Activity_DK extends AppCompatActivity {
//    EditText ed_hoten, ed_email, ed_sdt, ed_gt;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dk);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        ed_hoten = findViewById(R.id.ed_hoten_dk);
//        ed_email = findViewById(R.id.ed_email_dk);
//        ed_sdt = findViewById(R.id.ed_sdt_dk);
//        ed_gt = findViewById(R.id.ed_gioithieu_dk);
//    }
//
//    public void BackHome_wv(View view){
//        onBackPressed();
//    }
//
//    @Override
//    public void onBackPressed() {
//        Intent intent = getIntent();
//        String dk_hoten = ed_hoten.getText().toString();
//        String dk_email = ed_email.getText().toString();
//        String dk_sdt = ed_sdt.getText().toString();
//        String dk_gt = ed_gt.getText().toString();
//
//        intent.putExtra("hoTen", dk_hoten);
//        intent.putExtra("eMail", dk_email);
//        intent.putExtra("sDt", dk_sdt);
//        intent.putExtra("gT", dk_gt);
//
//        setResult(Activity.RESULT_OK,intent);
//
//        super.onBackPressed();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == android.R.id.home)
//            this.onBackPressed();
//
//        return super.onOptionsItemSelected(item);
//    }
//}