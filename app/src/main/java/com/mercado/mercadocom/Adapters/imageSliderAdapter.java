package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mercado.mercadocom.Model.imageSliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import com.mercado.mercadocom.R;

import java.util.List;


public class imageSliderAdapter extends SliderViewAdapter<SliderViewHolder> {
    Context context;
    List<imageSliderModel> imageSliderModelList;

    public imageSliderAdapter(Context context, List<imageSliderModel> imageSliderModelList) {
        this.context = context;
        this.imageSliderModelList = imageSliderModelList;
    }


    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_layout,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        viewHolder.sliderImageview.setImageResource(imageSliderModelList.get(position).getImage());


    }

    @Override
    public int getCount() {
        return imageSliderModelList.size();
    }
}
class  SliderViewHolder extends SliderViewAdapter.ViewHolder {
    ImageView sliderImageview;
    public SliderViewHolder(View itemView) {
        super(itemView);
        sliderImageview=itemView.findViewById(R.id.imageView);
    }
}

