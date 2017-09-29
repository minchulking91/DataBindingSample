package skoopmedia.co.kr.databindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import skoopmedia.co.kr.databindingsample.databinding.ActivityMainBinding

/**
 * Created by MinChulKim on 2017-09-29.
 */
class CommentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment_detail)
        val user = User("Hello", "World")
        binding.user = user


    }
}