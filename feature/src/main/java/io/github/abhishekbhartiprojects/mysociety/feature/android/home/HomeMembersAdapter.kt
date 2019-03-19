package io.github.abhishekbhartiprojects.mysociety.feature.android.home

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import kotlinx.android.synthetic.main.home_members_adapter_item.view.*
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions



class HomeMembersAdapter(var membersList: List<MemberInfoRow>): RecyclerView.Adapter<HomeMembersAdapter.HMAViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HMAViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_members_adapter_item, parent, false)

        return HMAViewHolder(view)
    }

    override fun getItemCount(): Int {
        return membersList.size
    }

    override fun onBindViewHolder(holder: HMAViewHolder, position: Int) {
        holder.setView(membersList.get(position))
    }

    class HMAViewHolder(var view: View?) : RecyclerView.ViewHolder(view!!) {

        fun setView(memberInfo: MemberInfoRow){
            //set DP
            if(view != null && !TextUtils.isEmpty(memberInfo.imageurl)){
                val requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)

                var builder = Glide.with(view!!.context)
                    .load(memberInfo.imageurl)

                requestOptions.placeholder(R.drawable.ic_default_dp)
                builder.apply(requestOptions)
                builder.into(view!!.civ_dp)
            }

            if(memberInfo.isisadmin()){
                view!!.admin.visibility = View.VISIBLE
            } else {
                view!!.admin.visibility = View.GONE
            }

            view!!.member_name.text = memberInfo.name
            view!!.profession.text = memberInfo.profession
            view!!.flat.text = memberInfo.wing + " " + memberInfo.flatno

            if(memberInfo.isIsowner){
                view!!.owner.text = "Owner"
            } else {
                view!!.owner.text = "Tanent"
            }

            if(!TextUtils.isEmpty(memberInfo.bloodgroup)){
                view!!.blood_group_text.visibility = View.VISIBLE
                view!!.blood_group.visibility = View.VISIBLE
                view!!.blood_group.text = memberInfo.bloodgroup
            } else {
                view!!.blood_group_text.visibility = View.GONE
                view!!.blood_group.visibility = View.GONE
            }


        }
    }
}