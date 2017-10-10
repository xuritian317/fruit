package com.example.xu.myapplication.moduleType.presenter;

import android.os.Bundle;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.dao.BargainDao;
import com.example.xu.myapplication.moduleType.dao.FruitAllDao;
import com.example.xu.myapplication.moduleType.dao.FruitAreaDao;
import com.example.xu.myapplication.moduleType.dao.FruitTypeDao;
import com.example.xu.myapplication.moduleType.dao.PackSizeDao;
import com.example.xu.myapplication.moduleType.dao.PackTypeDao;
import com.example.xu.myapplication.moduleType.entity.Bargain;
import com.example.xu.myapplication.model.Fruit;
import com.example.xu.myapplication.moduleType.fragment.MenuContentFragment;
import com.example.xu.myapplication.moduleType.viewInterface.IMenuList;
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;

/**
 * Created by xu on 2017/9/13.
 */

public class MenuListPresenter extends BasePresenter {
    private static final String SAVE_STATE_POSITION = "save_state_position";
    private IMenuList view;

    public MenuListPresenter(IMenuList view) {
        this.view = view;
    }

    public void isSave(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            int mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            view.setCurrentPosition(mCurrentPosition);
            view.setAdapterCheck(mCurrentPosition);
        } else {
            view.setCurrentPosition(1);
            view.setAdapterCheck(1);
        }
    }

    public void showContent(int position) {
        if (position == 0 || position == 3 || position == 5 || position == view.getCurrentPosition())
            return;
        view.setCurrentPosition(position);
        view.setAdapterCheck(position);
        switch (position) {
            case 1:
                PackTypeDao.newInstance(new PackTypeDao.CallBackPackType() {
                    @Override
                    public void onSuccess(ArrayList<Fruit> response) {
                        MenuContentFragment fragment = MenuContentFragment.newInstance(response, false);
                        view.switchFragment(fragment);
                    }

                    @Override
                    public void onFailure(String message) {
                        Logger.e("FruitTypeDao", "onFailure");
                    }
                }).getPackType();

                break;
            case 2:
                PackSizeDao.newInstance(new PackSizeDao.CallBackPackSize() {
                    @Override
                    public void onSuccess(ArrayList<Fruit> response) {
                        MenuContentFragment fragment = MenuContentFragment.newInstance(response, false);
                        view.switchFragment(fragment);
                    }

                    @Override
                    public void onFailure(String message) {
                        Logger.e("FruitTypeDao", "onFailure");
                    }
                }).getPackType();

                break;
            case 4:
                FruitAllDao.newInstance(new FruitAllDao.CallBackFruitAll() {
                    @Override
                    public void onSuccess(ArrayList<Fruit.FruitDetail> response) {
                        ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
                        fruitList.add(new Fruit(Common.NAME_FRUIT_TYPE, response));
                        MenuContentFragment fragment = MenuContentFragment.newInstance(fruitList, true);
                        view.switchFragment(fragment);
                    }

                    @Override
                    public void onFailure(String message) {
                        Logger.e("FruitTypeDao", "onFailure");
                    }
                }).getFruitType();
                break;
            case 6:
                FruitTypeDao.newInstance(new FruitTypeDao.CallBackFruitType() {
                    @Override
                    public void onSuccess(ArrayList<Fruit> response) {

                        MenuContentFragment fragment = MenuContentFragment.newInstance(response, false);
                        view.switchFragment(fragment);
                    }

                    @Override
                    public void onFailure(String message) {
                        Logger.e("FruitTypeDao", "onFailure");
                    }
                }).getFruitType();
                break;
            case 7:
                FruitAreaDao.newInstance(new FruitAreaDao.CallBackFruitArea() {
                    @Override
                    public void onSuccess(ArrayList<Fruit> response) {
                        MenuContentFragment fragment = MenuContentFragment.newInstance(response, false);
                        view.switchFragment(fragment);
                    }

                    @Override
                    public void onFailure(String message) {
                        Logger.e("FruitAreaDao", "onFailure");
                    }
                }).getFruitArea();
                break;

            default:
                break;
        }

    }
}
