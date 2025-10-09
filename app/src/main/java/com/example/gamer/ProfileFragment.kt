package com.example.gamer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToNews: () -> Unit,
    onNavigateToStore: () -> Unit,
    onNavigateBack: () -> Unit,
    showSnack: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(2) }
    val isDarkMode = isSystemInDarkTheme()
    val primaryColor = Color(0xFFE91E63)
    val barColor = if (isDarkMode) Color.Black else primaryColor
    val backgroundColor = if (isDarkMode) Color(0xFF121212) else Color.White
    val cardBackground = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.White.copy(alpha = 0.6f) else Color.Gray

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { showSnack("Notifications") }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = barColor
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = barColor
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "News",
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            "News",
                            color = Color.White
                        )
                    },
                    selected = selectedTab == 0,
                    onClick = {
                        selectedTab = 0
                        onNavigateToNews()
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.6f),
                        unselectedTextColor = Color.White.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Store",
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            "Store",
                            color = Color.White
                        )
                    },
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                        onNavigateToStore()
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.6f),
                        unselectedTextColor = Color.White.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            "Profile",
                            color = Color.White
                        )
                    },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.6f),
                        unselectedTextColor = Color.White.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Profile Avatar
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE91E63)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Full Name
            Text(
                text = "FullName",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Email
            Text(
                text = "Email",
                fontSize = 14.sp,
                color = secondaryTextColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Edit Profile Button
            Button(
                onClick = { showSnack("Edit Profile clicked") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE91E63)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Edit Profile",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Bookmarks Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Bookmarks",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Red Dead Redemption 2
                BookmarkCard(
                    imageRes = R.drawable.readdead2,
                    title = "Red Dead Redemption 2",
                    category = "PS4 Games",
                    price = "70.0 $",
                    isDarkMode = isDarkMode,
                    onBookmarkClick = { showSnack("Bookmark clicked") }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Grand Theft Auto 5
                BookmarkCard(
                    imageRes = R.drawable.gta,
                    title = "Grand Theft Auto 5",
                    category = "PS4 Games",
                    price = "60.0 $",
                    isDarkMode = isDarkMode,
                    onBookmarkClick = { showSnack("Bookmark clicked") }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BookmarkCard(
    imageRes: Int,
    title: String,
    category: String,
    price: String,
    isDarkMode: Boolean,
    onBookmarkClick: () -> Unit
) {
    val cardBackground = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.White.copy(alpha = 0.6f) else Color.Gray
    val primaryColor = Color(0xFFE91E63)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Game Image
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .width(70.dp)
                    .height(96.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Game Info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = category,
                    fontSize = 14.sp,
                    color = secondaryTextColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
            }

            // Bookmark Icon
            IconButton(
                onClick = onBookmarkClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Bookmark",
                    tint = primaryColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}