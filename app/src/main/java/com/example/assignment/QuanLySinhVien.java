package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.Adapter.LopAdapter;
import com.example.assignment.DTO.Lophoc;
import com.example.assignment.LopDao.ClassDao;

import java.util.ArrayList;

public class QuanLySinhVien extends AppCompatActivity {
    public View layout_themlop;
    LayoutInflater inflater;
    Dialog dialogAdd;
    ClassDao classDao;
    ArrayList<Lophoc> arrayList;
    LopAdapter lopAdapter;
    WebView webView;

    public void ShowDialogAdd(View view){
        dialogAdd.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        classDao.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlysinhvien);


        webView = findViewById(R.id.wv_gthieu);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress){
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                super.onReceivedError(view, request, error);
            }
        });

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        layout_themlop = inflater.inflate(R.layout.layout_themlop,(ViewGroup) findViewById(R.id.layout_addclass));

        if (layout_themlop.getParent()!=null){
            ((ViewGroup) layout_themlop.getParent()).removeAllViews();
        }

        final EditText ed_malop = layout_themlop.findViewById(R.id.ed_malop);
        final EditText ed_tenlop = layout_themlop.findViewById(R.id.ed_tenlop);

        Button btn_xoa = layout_themlop.findViewById(R.id.btn_xoatrang);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_malop.setText("");
                ed_tenlop.setText("");
            }
        });


        Button btn_luulop = layout_themlop.findViewById(R.id.btn_luulop);
        btn_luulop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lophoc lophoc = new Lophoc();
                lophoc.setMalop(ed_malop.getText().toString());
                lophoc.setTenlop(ed_tenlop.getText().toString());
                long kq = classDao.addNew(lophoc);
                if (kq>0){
                    Toast.makeText(getBaseContext(), "Lưu lớp thành công",Toast.LENGTH_SHORT).show();
                    arrayList.clear();
                    arrayList.addAll(classDao.selectAll());
                    lopAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getBaseContext(), "Lưu lớp thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn_showclass = findViewById(R.id.btn_showclass);
        btn_showclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), List_Class.class);
                startActivity(intent);
            }
        });

        Button btn_quanlysv = findViewById(R.id.btn_quanlysv);
        btn_quanlysv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),List_Student.class));
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout_themlop);
        builder.setTitle("Add New Class");
        builder.setIcon(android.R.drawable.ic_input_add);
//        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String e_malop = ed_malop.getText().toString();
//                String e_tenlop = ed_tenlop.getText().toString();
//                dialog.dismiss();
//            }
//        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogAdd = builder.create();
        classDao = new ClassDao(this);
        classDao.open();

        arrayList = classDao.selectAll();
        lopAdapter = new LopAdapter(arrayList);

    }

//    ActivityResultLauncher toolActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            String hoTen = result.getData().getStringExtra("hoTen");
//            String eMail = result.getData().getStringExtra("eMail");
//            String sDt = result.getData().getStringExtra("sDt");
//            String gThieu = result.getData().getStringExtra("gT");
//            String chuoiHTML = "<h1 style='color: red'>Thông tin giới thiệu</h1>\n"+"<h2>-Họ tên: </h2>"+ hoTen+"<h2>-Email: </h2>"+eMail+"<h2>-Số điện thoại: </h2>"+sDt+"<h2>-Giới thiệu: </h2>"+gThieu;
//
//            webView.loadData(chuoiHTML, "text/html", "utf-8");
//        }
//    });

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.sl_gt:
//                Intent intent = new Intent(this, Activity_DK.class);
//                toolActivityResultLauncher.launch(intent);
//                Toast.makeText(this, "Bạn chọn giới thiệu", Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//        int id = item.getItemId();
//        if(id==android.R.id.home){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}