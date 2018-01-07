package com.spring_ballet.keep.fragment.Book;

import android.support.v7.widget.GridLayoutManager;

import com.spring_ballet.keep.Adapter.BookRecyclerViewAdapter;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.DiffTypeNumber;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.base.BaseClass;
import com.spring_ballet.keep.base.BaseFragment;
import com.spring_ballet.keep.bean.Book;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.FragmentBookTypeBinding;

import java.util.List;

public class ShengHuoFragment extends BaseFragment<FragmentBookTypeBinding> {

    private boolean isFirst = true;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_book_type;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isFirst) {
            isFirst = false;
            MyAsyncTask myAsyncTask = new MyAsyncTask(DiffTypeNumber.BOOKTAG);
            myAsyncTask.execute(ApiList.SearchBookUrl + ApiList.BookSearchTags[2]);
            myAsyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
                @Override
                public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {
                }

                @Override
                public void onOtherDataReceivedSuccess(BaseClass baseClass) {
                    if (baseClass instanceof Book) {
                        Book book = (Book) baseClass;
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);
                        binding.recyclerviewBookType.setLayoutManager(gridLayoutManager);
                        binding.recyclerviewBookType.setAdapter(new BookRecyclerViewAdapter(activity, book.getBooks()));
                    }
                }

                @Override
                public void onDataReceivedFailed() {
                    ToastUtil.showToast(activity, "加载出错了");
                }
            });
        }
    }
}
