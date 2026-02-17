package com.example.pruebascompose

sealed class BottomBartems{
    sealed class BottomBarItem(
        val titleRes: Int,
        val selectedIcon: Int,
        val unselectedIcon: Int
    ) {
        object Home : BottomBarItem(
            titleRes = R.string.tab_home,
            selectedIcon = R.drawable.ic_home_checked,
            unselectedIcon = R.drawable.ic_home
        )

        object Search : BottomBarItem(
            titleRes = R.string.main_search,
            selectedIcon = R.drawable.ic_search_checked,
            unselectedIcon = R.drawable.ic_search
        )

        object Space : BottomBarItem(
            titleRes = R.string.myspace_title,
            selectedIcon = R.drawable.ic_space_checked,
            unselectedIcon = R.drawable.ic_space
        )

        object Settings : BottomBarItem(
            titleRes = R.string.tab_config,
            selectedIcon = R.drawable.ic_profile_checked,
            unselectedIcon = R.drawable.ic_profile
        )
        

    }
}