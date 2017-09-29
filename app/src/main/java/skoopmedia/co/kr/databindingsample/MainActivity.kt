package skoopmedia.co.kr.databindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import skoopmedia.co.kr.databindingsample.databinding.ActivityMainBinding
import skoopmedia.co.kr.databindingsample.databinding.ViewholderCommentBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val user = User("Hello", "World")
        binding.user = user

        Handler().postDelayed({
            user.firstName = "kim"
        }, 1000)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentAdapter()
        refreshComments()

        swipeRefreshLayout.setOnRefreshListener {
            refreshComments()
        }
    }

    private fun refreshComments() {
        CommentRepository.instance.getCommentsFromServer(onFinish = {
            swipeRefreshLayout.isRefreshing = false
            recyclerView.adapter.notifyDataSetChanged()
        })
    }

    class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
        private val items = CommentRepository.instance.getCommentList()
        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
            val item = items[position]
            holder?.bind(item)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
            val inflater = LayoutInflater.from(parent?.context)
            val binding: ViewholderCommentBinding = DataBindingUtil.inflate(inflater, R.layout.viewholder_comment, parent, false)
            return CommentViewHolder(binding)
        }

    }

    class CommentViewHolder(private val binding: ViewholderCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.item = comment
        }
    }
}
