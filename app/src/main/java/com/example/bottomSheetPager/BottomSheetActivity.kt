package com.example.bottomSheetPager

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        view_pager.adapter = Adapter(supportFragmentManager)
        view_pager.addOnPageChangeListener(mPageChangeListener)
        view_pager.clipToPadding = false
        view_pager.setPadding(30,0,30,0)
        // ページ間のマージン
        view_pager.pageMargin = 15
        val layoutParams = (wrapLayout as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.addBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
        setHeaderTitle(0)
    }

    private val mPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            // Do nothing
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            // Do nothing
        }

        override fun onPageSelected(position: Int) {
            setHeaderTitle(position)
        }

    }

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            // ダウンスワイプを検知して画面をfinish
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                finish()
                overridePendingTransition(0, 0)
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    private fun setHeaderTitle(position: Int) {
        if (position > 2) {
            headerTextView.text = "その他"
        } else {
            headerTextView.text = "TopNews"
        }
    }

    class Adapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return PAGE_COUNT
        }

        override fun getItem(position: Int): Fragment {
            return PageFragment.newInstance()
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "${position + 1}ページ目"
        }

    }

    companion object {
        private const val PAGE_COUNT = 5
    }
}