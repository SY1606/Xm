package com.example.tt_xm.ui.activity_two;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tt_xm.R;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.bean.Header;
import com.example.tt_xm.data.bean.Person;
import com.example.tt_xm.di.contract.PersonContract;
import com.example.tt_xm.di.presenter.PersonPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyDatasActivity extends AppCompatActivity implements PersonContract.PersonDataView {

    @BindView(R.id.activity_personal_sdv)
    SimpleDraweeView activityPersonalSdv;
    @BindView(R.id.activity_personal_text_nickname)
    TextView activityPersonalTextNickname;
    @BindView(R.id.activity_personal_text_password)
    TextView activityPersonalTextPassword;
    private PersonContract.PersonDataPresenter personDataPresenter;
     String pwd;
    private File file;
     List<MultipartBody.Part> parts;
     String userId;
    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_datas);
        ButterKnife.bind(this);

        //个人信息
        personDataPresenter = new PersonPresenter();
        personDataPresenter.attchPersonDataView(this);
        personDataPresenter.requestPersonDataData();


        //修改个人信息
        activityPersonalTextNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyDatasActivity.this, UpdateActivity.class));
            }
        });

        /*activityPersonalTextPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalDataActivity.this, AmendPwdMessageActivity.class));
            }
        });
*/
        activityPersonalSdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    //点击popWindow
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.mypopupwindow, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPopWindow.setContentView(contentView);
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_my_datas, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

        TextView pop_camera = contentView.findViewById(R.id.pop_camera);
        TextView pop_gallery = contentView.findViewById(R.id.pop_gallery);
        TextView pop_calcen = contentView.findViewById(R.id.pop_calcen);


        pop_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 第三步:创建 意图
                // Intent.ACTION_PICK 这个对应的就是图库
                Intent intent = new Intent(Intent.ACTION_PICK);
                // 第四步:设置显式MIME数据类型
                intent.setType("image/*");
                // 第五步：跳转回
                startActivityForResult(intent, 2);
                mPopWindow.dismiss();
            }
        });
        pop_calcen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });

    }

    //个人基本信息
    @Override
    public void getPersonDataData(String responseData) {
        Gson gson = new Gson();
        Log.e("gs_per", "responseData:-------个人信息 " + responseData);
        Person person = gson.fromJson(responseData, Person.class);
        Person.ResultBean result = person.getResult();

        activityPersonalTextNickname.setText(result.getNickName() + "");
        activityPersonalTextPassword.setText(pwd);
        Glide.with(this).load(result.getHeadPic()).into(activityPersonalSdv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:

                //相机

                //得到位图
                Bitmap bitmap2 = data.getParcelableExtra("data");
                //设置位图
                activityPersonalSdv.setImageBitmap(bitmap2);

                break;
            case 2:


                //personalDataImg.setImageURI(uri);
                try {
                    Uri uri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    activityPersonalSdv.setImageBitmap(bitmap);
                    //***
                    //获取commodityId
                    //获取图片转换的文件进行提交
                    file = compressImage(bitmap);
                    //指定提交类型
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    //多部分的表单请求体参数提交
                    MultipartBody body = new MultipartBody.Builder()
                            .addFormDataPart("image", file.getName(), requestBody)
                            .build();

                    parts = body.parts();

                    //图片上传
                    OkGo.<String>post(Constant.MODIFYHEADPIC_URL)
                            .headers("userId",userId)
                            .headers("sessionId",sessionId)
                            .params("image",file)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String s = response.body().toString();
                                    Gson gson = new Gson();
                                    Header header = gson.fromJson(s, Header.class);
                                    Toast.makeText(MyDatasActivity.this, header.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });



                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            default:
                break;
        }
    }

    private File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        //以当前时间命名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        //图片名
        String filename = format.format(date);
        //存储到外存空间
        File file = new File(Environment.getExternalStorageDirectory(), filename + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;

    }


}
