package juloo.keyboard2;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClipboardView extends LinearLayout implements ClipboardHistoryService.OnClipboardHistoryChange {

    private ClipboardHistoryService service;
    private ClipboardAdapter adapter;
    private RecyclerView recyclerView;

    public ClipboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        service = ClipboardHistoryService.get_service(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        adapter = new ClipboardAdapter();
        recyclerView = findViewById(R.id.clipboard_recycler_view);
        // Adjust span count based on screen width later if needed
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new GestureCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Button importButton = findViewById(R.id.clipboard_import_button);
        importButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ImportClipboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        });

        Button exportButton = findViewById(R.id.clipboard_export_button);
        exportButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ExportClipboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (service != null) {
            service.setOnClipboardHistoryChange(this);
            updateData();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (service != null) {
            service.setOnClipboardHistoryChange(null);
        }
    }

    @Override
    public void on_clipboard_history_change() {
        updateData();
    }

    private void updateData() {
        if (service != null && adapter != null) {
            adapter.setItems(service.getItems());
        }
    }

    private class GestureCallback extends ItemTouchHelper.SimpleCallback {
        GestureCallback() {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            ClipboardItem item = adapter.getItem(position);
            if (item == null) return;

            switch (direction) {
                case ItemTouchHelper.LEFT:
                case ItemTouchHelper.RIGHT:
                    service.removeItem(item);
                    break;
                case ItemTouchHelper.UP:
                    ClipboardHistoryService.paste(item.getText());
                    adapter.notifyItemChanged(position); // Snap back
                    break;
                case ItemTouchHelper.DOWN:
                    if (!item.isPinned()) {
                        service.togglePin(item);
                    }
                    // Snap back even if already pinned and no action was taken
                    adapter.notifyItemChanged(position);
                    break;
            }
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }
    }

    private class ClipboardAdapter extends RecyclerView.Adapter<ClipboardAdapter.ViewHolder> {
        private List<ClipboardItem> items = new ArrayList<>();

        void setItems(List<ClipboardItem> newItems) {
            this.items = newItems;
            notifyDataSetChanged();
        }

        ClipboardItem getItem(int position) {
            if (position >= 0 && position < items.size()) {
                return items.get(position);
            }
            return null;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.clipboard_grid_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ClipboardItem item = items.get(position);
            holder.textView.setText(item.getText());
            holder.pinIcon.setVisibility(item.isPinned() ? View.VISIBLE : View.GONE);

            holder.itemView.setOnLongClickListener(v -> {
                if (service != null) {
                    service.togglePin(item);
                }
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView textView;
            final ImageView pinIcon;

            ViewHolder(View view) {
                super(view);
                textView = view.findViewById(R.id.clipboard_item_text);
                pinIcon = view.findViewById(R.id.clipboard_item_pin_icon);
            }
        }
    }
}