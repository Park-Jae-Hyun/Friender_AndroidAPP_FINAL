//리스트뷰의 각 도시를 보여줄 리니어레이아웃
package com.example.jteam.friender.bulletinview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jteam.friender.R;

/**
 * Created by Jeong on 2016-07-27.
 */
public class BulletinItemView extends LinearLayout{
    TextView DestinationView;
    TextView RouteView1;
    TextView RouteView2;
    TextView DateView;
    TextView PresentView;
    TextView FindingView;
    ImageView[] PictogramView = new ImageView[3];

    //pictogram resource 배열
    int pictogramres[] = {R.color.white,R.mipmap.p01people,R.mipmap.p02food,R.mipmap.p03beer,R.mipmap.p04coffee,
    R.mipmap.p05sports,R.mipmap.p06music,R.mipmap.p07movie,R.mipmap.p08photo,R.mipmap.p09reading,
    R.mipmap.p10concert,R.mipmap.p11festival,R.mipmap.p12travel,R.mipmap.p13rest,R.mipmap.p14tour,
    R.mipmap.p15beach,R.mipmap.p16mountain,R.mipmap.p17owncar,R.mipmap.p18bycicle,
    R.mipmap.p19publictransit,R.mipmap.p20cruise};

    public BulletinItemView(Context context) {
        super(context);

        init(context);
    }

    public BulletinItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        //initializing on each list view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.board_item,this,true);

        DestinationView = (TextView) findViewById(R.id.destination);
        RouteView1 = (TextView) findViewById(R.id.route1);
        RouteView2 = (TextView) findViewById(R.id.route2);
        DateView = (TextView) findViewById(R.id.date);
        PresentView = (TextView) findViewById(R.id.present);
        FindingView = (TextView) findViewById(R.id.finding);
        PictogramView[0] = (ImageView) findViewById(R.id.pictogram1);
        PictogramView[1] = (ImageView) findViewById(R.id.pictogram2);
        PictogramView[2] = (ImageView) findViewById(R.id.pictogram3);
    }

    public void setDateView(String date) {
        DateView.setText(date);
    }

    public void setDestinationView(String destination) {
        DestinationView.setText(destination);
    }

    public void setFindingView(String finding) {
        FindingView.setText(finding);
    }

    public void setPresentView(String present) {
        PresentView.setText(present);
    }

    public void setRoute1View(String route) {
        RouteView1.setText(route);
    }

    public void setRoute2View(String route) {
        RouteView2.setText(route);
    }

    public void setPictogramView(int pictogram1, int pictogram2, int pictogram3) {
        PictogramView[0].setImageResource(pictogram1);
        PictogramView[1].setImageResource(pictogram2);
        PictogramView[2].setImageResource(pictogram3);
    }

    public void setBulletin(Bulletin bulletin)
    {
        DateView.setText(bulletin.getDate());
        DestinationView.setText(bulletin.getDestination());
        if(bulletin.getTotalnum() == 10)
            FindingView.setText("Finding:Any");
        else
            FindingView.setText("Finding : "+bulletin.getTotalnum());
        PresentView.setText("Presenting : "+bulletin.getJoinednum());
        RouteView1.setText(bulletin.getRoute1());
        RouteView2.setText(bulletin.getRoute2());
        PictogramView[0].setImageResource(pictogramres[bulletin.getCharacter(0)]);
        PictogramView[1].setImageResource(pictogramres[bulletin.getCharacter(1)]);
        PictogramView[2].setImageResource(pictogramres[bulletin.getCharacter(2)]);

    }

}
