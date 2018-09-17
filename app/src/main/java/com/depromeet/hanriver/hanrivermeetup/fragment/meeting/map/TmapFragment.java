package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.depromeet.hanriver.hanrivermeetup.BuildConfig;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.skt.Tmap.TMapView;

public class TmapFragment extends Fragment {

    RelativeLayout map;
    TMapView mapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        map = view.findViewById(R.id.mapView);
        mapView = new TMapView(getActivity());
        // mapView.setSKTMapApiKey(BuildConfig.TMapApiKey);

        mapView.setLocationPoint(126.970325,37.556152);
        mapView.setCenterPoint(126.970325,37.556152);
        mapView.setCompassMode(false);
        mapView.setIconVisibility(true);
        mapView.setZoomLevel(15);
        mapView.setMapType(TMapView.MAPTYPE_STANDARD);  //일반지도
        mapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        mapView.setTrackingMode(false);
        mapView.setSightVisible(false);
        map.addView(mapView);

        return view;
    }
}
