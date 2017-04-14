package com.studyingapp.southfang.moralebooster.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.studyingapp.southfang.moralebooster.MessageItem;
import com.studyingapp.southfang.moralebooster.R;
import com.studyingapp.southfang.moralebooster.fragments.MessageFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MessageItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MessageItemRecyclerViewAdapter extends RecyclerView.Adapter<MessageItemRecyclerViewAdapter.ViewHolder> {

	private final List<MessageItem> mValues;
	private final OnListFragmentInteractionListener mListener;

	public MessageItemRecyclerViewAdapter(List<MessageItem> items, OnListFragmentInteractionListener listener) {
		mValues = items;
		mListener = listener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_messageitem, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.mItem = mValues.get(position);
		holder.mFechaView.setText(mValues.get(position).getFecha());
		holder.mContentView.setText(mValues.get(position).getContenido());
		holder.mTipoContenido.setText(mValues.get(position).getTipoMensaje().toString());
		holder.mImagen.setImageResource(mValues.get(position).getImagen());

		holder.mView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != mListener) {
					mListener.onListFragmentInteraction(holder.mItem);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return mValues.size();
	}

	@SuppressWarnings("WeakerAccess")
	public class ViewHolder extends RecyclerView.ViewHolder {
		public final View mView;
		public final TextView mFechaView;
		public final TextView mContentView;
		public final TextView mTipoContenido;
		public final ImageView mImagen;
		public MessageItem mItem;

		public ViewHolder(View view) {
			super(view);
			mView = view;
			mFechaView = (TextView) view.findViewById(R.id.fecha);
			mContentView = (TextView) view.findViewById(R.id.content);
			mTipoContenido = (TextView) view.findViewById(R.id.tipo_contenido);
			mImagen = (ImageView) view.findViewById(R.id.imagen);
		}

		@Override
		public String toString() {
			return super.toString() + " '" + mContentView.getText() + "'";
		}
	}
}
