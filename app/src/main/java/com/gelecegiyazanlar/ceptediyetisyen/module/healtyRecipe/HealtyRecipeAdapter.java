package com.gelecegiyazanlar.ceptediyetisyen.module.healtyRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.gelecegiyazanlar.ceptediyetisyen.R;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.callback.IFirebaseCallbackListener;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.RowHealtyRecipeBinding;
import com.gelecegiyazanlar.ceptediyetisyen.model.HealtyRecipe;
import java.util.ArrayList;
import java.util.List;



public class HealtyRecipeAdapter extends RecyclerView.Adapter<HealtyRecipeAdapter.BrowseItemHolder> {

    private List<String> ids = new ArrayList<>();

    private List<HealtyRecipe> items = new ArrayList<>();
    private LayoutInflater inflater;
    private IFirebaseCallbackListener<HealtyRecipe> iFirebaseCallbackListener;

    private HealtyRecipeFragment fragment;

    HealtyRecipeAdapter(HealtyRecipeFragment fragment) {
        this.fragment = fragment;
        inflater = (LayoutInflater) fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.iFirebaseCallbackListener = new IFirebaseCallbackListener<HealtyRecipe>() {

            @Override
            public void childAdded(HealtyRecipe healtyRecipe) {
                ids.add(healtyRecipe.getId());
                items.add(healtyRecipe);
                notifyItemInserted(items.size() - 1);
            }

            @Override
            public void childChanged(HealtyRecipe healtyRecipe) {
                String id = healtyRecipe.getId();

                int index = ids.indexOf(id);
                if (index > -1) {
                    items.set(index, healtyRecipe);
                    notifyItemChanged(index);
                }
            }

            @Override
            public void childRemoved(HealtyRecipe healtyRecipe) {
                String id = healtyRecipe.getId();

                int index = ids.indexOf(id);
                if (index > -1) {
                    ids.remove(index);
                    items.remove(index);
                    notifyItemRemoved(index);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
    }

    @Override
    public BrowseItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowHealtyRecipeBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_healty_recipe, parent, false);
        return new BrowseItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(BrowseItemHolder holder, int position) {
        HealtyRecipe model = items.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    IFirebaseCallbackListener<HealtyRecipe> getiFirebaseCallbackListener() {
        return iFirebaseCallbackListener;
    }

    public List<HealtyRecipe> getItems() {
        return items;
    }

    public interface HealtyRecipeItemListener {
        void onClick();
    }

    class BrowseItemHolder extends RecyclerView.ViewHolder implements HealtyRecipeItemListener {

        private RowHealtyRecipeBinding binding;

        BrowseItemHolder(RowHealtyRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(HealtyRecipe item) {
            binding.setPresenter(this);
            binding.setData(item);
        }

        @Override
        public void onClick() {

        }
    }
}
