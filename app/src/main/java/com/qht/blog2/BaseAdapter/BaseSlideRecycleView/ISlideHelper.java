package com.qht.blog2.BaseAdapter.BaseSlideRecycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhan on 2017/2/6.
 */

public class ISlideHelper {

  private List<com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlide> mISlides = new ArrayList<>();

  public void add(com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlide iSlide) {
    mISlides.add(iSlide);
  }

  public void remove(com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlide iSlide) {
    mISlides.remove(iSlide);
  }

  public void clear() {
    mISlides.clear();
  }

  public List<com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlide> getISlideList() {
    return mISlides;
  }

  public void slideOpen() {
    for (com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlide slide : mISlides) {
      slide.slideOpen();
    }
  }

  public void slideClose() {
    for (com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlide slide : mISlides) {
      slide.slideClose();
    }
  }
}
