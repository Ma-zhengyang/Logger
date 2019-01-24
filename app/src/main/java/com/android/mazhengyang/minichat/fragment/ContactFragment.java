package com.android.mazhengyang.minichat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.mazhengyang.minichat.R;
import com.android.mazhengyang.minichat.adapter.ContactAdapter;
import com.android.mazhengyang.minichat.bean.UserBean;
import com.android.mazhengyang.minichat.model.IContactCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by mazhengyang on 18-12-6.
 */

public class ContactFragment extends Fragment implements
        AdapterView.OnItemClickListener, StickyListHeadersListView.OnHeaderClickListener,
        StickyListHeadersListView.OnStickyHeaderOffsetChangedListener,
        StickyListHeadersListView.OnStickyHeaderChangedListener {

    private static final String TAG = "MiniChat." + ContactFragment.class.getSimpleName();

    private IContactCallback userListCallback;

    private List<UserBean> contactList;
    private ContactAdapter userListAdapter;

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.stickyListHeadersListView)
    StickyListHeadersListView stickyListHeadersListView;

    public void setUserListCallback(IContactCallback userListCallback) {
        this.userListCallback = userListCallback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_contact, null);
        ButterKnife.bind(this, view);

        tvHead.setText(R.string.tab_userlist);

        Context context = getContext();

        userListAdapter = new ContactAdapter(context, contactList);

        stickyListHeadersListView.setOnItemClickListener(this);
        stickyListHeadersListView.setOnHeaderClickListener(this);
        stickyListHeadersListView.setOnStickyHeaderChangedListener(this);
        stickyListHeadersListView.setOnStickyHeaderOffsetChangedListener(this);
//        stickyListHeadersListView.addHeaderView(getLayoutInflater().inflate(R.layout.list_header, null));
//        stickyListHeadersListView.addFooterView(getLayoutInflater().inflate(R.layout.list_footer, null));
//        stickyListHeadersListView.setEmptyView(findViewById(R.id.empty));
        stickyListHeadersListView.setDrawingListUnderStickyHeader(true);
        stickyListHeadersListView.setAreHeadersSticky(true);
        stickyListHeadersListView.setAdapter(userListAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void setArguments(Bundle args) {
        Log.d(TAG, "setArguments: ");
        super.setArguments(args);
    }

    public void freshUserList(List<UserBean> list) {
        this.contactList = list;
        userListAdapter.freshUserList(list);
    }

    @Override
    public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {

    }

    @Override
    public void onStickyHeaderOffsetChanged(StickyListHeadersListView l, View header, int offset) {

    }

    @Override
    public void onStickyHeaderChanged(StickyListHeadersListView l, View header, int itemPosition, long headerId) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: position=" + position);
        if (userListCallback != null) {
            userListCallback.onUserItemClick(contactList.get(position));
        }
    }
}