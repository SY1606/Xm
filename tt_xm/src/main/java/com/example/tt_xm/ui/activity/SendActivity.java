package com.example.tt_xm.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.net.PermissionsUtils;
import com.example.tt_xm.di.contract.SendContract;
import com.example.tt_xm.di.presenter.SendPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SendActivity extends AppCompatActivity implements SendContract.SendView {


    @BindView(R.id.createCircle_edt)
    EditText createCircleEdt;
    @BindView(R.id.createCircle_imagebtn)
    ImageView createCircleImagebtn;
    @BindView(R.id.createCircle_btnBiao)
    Button createCircleBtnBiao;
    private SendContract.SendPresenter sendPresenter;
    private String content;
    private MultipartBody.Part image;
    private int commodityId = 27;
    String[] permiss = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ButterKnife.bind(this);

        sendPresenter = new SendPresenter();
        sendPresenter.attahView(this);

        content = createCircleEdt.getText().toString();
    }

    @Override
    public void showData(String requestData) {
        Toast.makeText(this, requestData, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.createCircle_imagebtn, R.id.createCircle_btnBiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.createCircle_imagebtn:

                View p = View.inflate(SendActivity.this, R.layout.pip, null);
                final PopupWindow popupWindow = new PopupWindow(p, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                popupWindow.showAtLocation(view, Gravity.RIGHT | Gravity.BOTTOM, 10, 10);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                Button cream = p.findViewById(R.id.xiangji);
                Button photo = p.findViewById(R.id.xiangce);
                Button centel = p.findViewById(R.id.quxiao);
                cream.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PermissionsUtils.getInstance().chekPermissions(SendActivity.this, permiss, new PermissionsUtils.IPermissionsResult() {
                            @Override
                            public void passPermissons() {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.addCategory("android.intent.category.DEFAULT");
                                startActivityForResult(intent, 101);
                                popupWindow.dismiss();
                            }

                            @Override
                            public void forbitPermissons() {
                            }
                        });
                    }
                });
                photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PermissionsUtils.getInstance().chekPermissions(SendActivity.this, permiss, new PermissionsUtils.IPermissionsResult() {
                            @Override
                            public void passPermissons() {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 202);
                                popupWindow.dismiss();
                            }

                            @Override
                            public void forbitPermissons() {

                            }
                        });
                    }
                });
                centel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                break;
            case R.id.createCircle_btnBiao:

                sendPresenter.requestData(commodityId,content,image);
                finish();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Bitmap bitmap = data.getParcelableExtra("data");
            createCircleImagebtn.setImageBitmap(bitmap);
            //将bitmap类型转换成uri
            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
            addHeaderPhoto(uri);
        }
        if (requestCode == 202) {
            Uri uri = data.getData();
            addHeaderPhoto(uri);
            createCircleImagebtn.setImageURI(uri);
        }

    }

    private void addHeaderPhoto(Uri uri) {
        //将uri类型转换成file
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String string = cursor.getString(columnIndex);
        File file = new File(string);

        //将file类型转换成part
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
    }
}
