package com.example.respect.cashbackapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.respect.cashbackapp.Adapters.ImageAdapter;
import com.example.respect.cashbackapp.Constants;
import com.example.respect.cashbackapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowImageActivity extends AppCompatActivity  {


    private TypedArray imageList;
    private ImageView imageView;
    private Bitmap bitmap;
    private int position;
    private PhotoViewAttacher viewAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        imageView=findViewById(R.id.show_image);
        position=getIntent().getIntExtra(Constants.KEY_POSITION,0);
        imageList= ImageAdapter.getImages();
        imageView.setImageDrawable(imageList.getDrawable(position));
        registerForContextMenu(imageView);
        viewAttacher=new PhotoViewAttacher(imageView,true);
        viewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openContextMenu(imageView);
                return true;
            }
        });


    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        ActivityCompat.requestPermissions(ShowImageActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        BitmapDrawable drawable= (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/images");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);
        try {
            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Toast toast=Toast.makeText(this,"Image saved",Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(outFile));
        sendBroadcast(intent);

        return true;

    }




/*
        OutputStream outputStream;
        bitmap=BitmapFactory.decodeResource(imageList.getResources(),position);
        File filePath= Environment.getExternalStorageDirectory();
        File dir=new File(filePath.getAbsolutePath()+"/Save image");
        dir.mkdirs();

        File file=new File(dir,"myimage.png");

        try {
            outputStream=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/


        //to get the image from the ImageView (say iv)







}
