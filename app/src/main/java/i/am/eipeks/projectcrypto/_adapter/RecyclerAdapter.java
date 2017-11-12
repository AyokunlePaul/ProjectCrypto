package i.am.eipeks.projectcrypto._adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import i.am.eipeks.projectcrypto.R;
import i.am.eipeks.projectcrypto._model.Country;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    private List<Country> countries;
    private Context context;
    private CardViewClickListener cardViewClickListener;

    public RecyclerAdapter(List<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder((CardView) LayoutInflater.from(context)
                .inflate(R.layout.home_card_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Country currentCountry = countries.get(position);
        holder.countryName.setText(currentCountry.getCountryName());
        holder.countryCode.setText(currentCountry.getCountryCode());
        holder.ethValue.setText(String.format(Locale.ENGLISH,
                "%s %s", "ETH", Double.toString(currentCountry.getEthValue())));
        holder.btcValue.setText(String.format(Locale.ENGLISH,
                "%s %s", "BTC", Double.toString(currentCountry.getBtcValue())));
        this.setOnCardViewClickListener(new CardViewClickListener() {
            @Override
            public void onCardViewClicked(int position) {
                if (cardViewClickListener != null){
                    cardViewClickListener.onCardViewClicked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void setOnCardViewClickListener(CardViewClickListener cardViewClickListener){
        this.cardViewClickListener = cardViewClickListener;
    }

    public interface CardViewClickListener{
        void onCardViewClicked(int position);
    }

    static class Holder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView countryCode, countryName, btcValue, ethValue;

        Holder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
            countryCode = cardView.findViewById(R.id.country_code);
            countryName = cardView.findViewById(R.id.country_name);
            btcValue = cardView.findViewById(R.id.btc_value);
            ethValue = cardView.findViewById(R.id.eth_value);
        }
    }

}
