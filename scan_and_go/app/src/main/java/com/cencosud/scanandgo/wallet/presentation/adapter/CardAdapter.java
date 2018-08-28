package com.cencosud.scanandgo.wallet.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ItemCardBigBinding;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.presentation.adapter.BaseListAdapter;
import com.core.presentation.adapter.holder.BaseViewHolder;

/**
 * Created by carlos on 28-03-18.
 */

public class CardAdapter extends BaseListAdapter<Card,CardAdapter.ViewHolder> {

    private ActionsListener actionsListener;
    private CarouselLayoutManager layoutManager;
    public int itemPositionCentered;
    private boolean isSelectionMode;
    private CardSelectionListener selectionListener;

    public void setSelectionListener(CardSelectionListener selectionListener) { this.selectionListener = selectionListener;}

    public void setActionsListener(ActionsListener actionsListener) { this.actionsListener = actionsListener; }

    @Override protected ViewHolder createViewHolder(int viewType, View v) {return new ViewHolder(v);}

    @Override protected int getLayoutIdByType(int viewType) {return R.layout.item_card_big;}

    public void setLayoutManager(CarouselLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {
            @Override public void onCenterItemChanged(int adapterPosition) {
                try{
                    notifyItemChanged(itemPositionCentered);
                    itemPositionCentered=adapterPosition;
                }catch (Exception ignored){
                    ignored.printStackTrace();
                }
            }
        });
    }

    public void setSelectionMode(boolean selectionMode) {
        this.isSelectionMode = selectionMode;
    }

    public class ViewHolder extends BaseViewHolder<Card,ItemCardBigBinding> {

        ViewHolder(View itemView) { super(itemView); }

        @Override public void bind(int position, final Card item) {
            binder.front.tvNumber.setText(item.cardNumber);
            binder.front.ivType.setImageResource(item.getCardTypeImage());
            binder.front.tvNumberSmall.setText(item.cardNumber.substring(item.cardNumber.length()-4));
            binder.front.tvCardHolder.setText(item.cardHolderName);
            int cardBackground;
            if(item.nameCard !=null)
                cardBackground = item.nameCard.getCardBackground();
            else
                cardBackground=R.drawable.jcb_background;
            binder.front.ivDefault.setVisibility(item.defaultCard?View.VISIBLE:View.GONE);
            binder.front.cardBase.setBackgroundResource(cardBackground);
            if(!isSelectionMode){
                binder.back.backBase.setBackgroundResource(cardBackground);
                binder.flipView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        if(itemIsInCenter(getAdapterPosition())){
                            binder.flipView.flipTheView();
                        } else {
                            centerItem(getAdapterPosition(),view.getContext());
                        }
                    }
                });
                if(itemPositionCentered!=position && binder.flipView.isBackSide())
                    binder.flipView.flipTheView();
                binder.back.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        actionsListener.onDeleteCardClicked(getItem(getAdapterPosition()));
                    }
                });
                binder.back.btnSetAsDefault.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        binder.flipView.flipTheView();
                        actionsListener.onSetAsDefaultClicked(getItem(getAdapterPosition()));
                    }
                });
            }else{
                binder.flipView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        if(!itemIsInCenter(getAdapterPosition())){
                            centerItem(getAdapterPosition(),view.getContext());
                        }else {
                            binder.front.flSelectionDone.setVisibility(View.VISIBLE);
                            selectionListener.onCardSelected(item);
                        }
                    }
                });
            }

        }
    }

    public void centerItem(final int adapterPosition, Context context) {
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context) {
            @Override protected int getVerticalSnapPreference() {
                if(adapterPosition<layoutManager.getCenterItemPosition())
                    return LinearSmoothScroller.SNAP_TO_END;
                else return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(adapterPosition);
        layoutManager.startSmoothScroll(smoothScroller);
    }

    private boolean itemIsInCenter(int itemPosition) {
        return layoutManager.getCenterItemPosition()==itemPosition;

    }

    public interface ActionsListener {

        void onDeleteCardClicked(Card item);
        void onSetAsDefaultClicked(Card item);
    }

    public interface CardSelectionListener {
        void onCardSelected(Card item);
    }
}