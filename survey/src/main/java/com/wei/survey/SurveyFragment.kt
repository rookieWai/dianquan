package com.wei.survey

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.survey.databinding.FragmentSurveyBinding

/**
 * @ClassName FragmentSurvey
 * @Author Rookie Wai
 * @Date 2021/7/23 17:08
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class SurveyFragment : BaseFragment() {

    override fun getLayoutRes()=R.layout.fragment_survey

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentSurveyBinding.bind(view)
    }
}