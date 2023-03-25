package com.example.bookfinder.screens.common


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookfinder.R
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.bookfinder.model.Book
import com.example.bookfinder.model.SearchInfo
import com.example.bookfinder.ui.theme.BookFinderTheme
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun BookItem(book: Book) {

    val context = LocalContext.current

    var isFavorite by remember {
        mutableStateOf(book.isFavorite)
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(
                if (book.volumeInfo?.imageLinks?.thumbnail.isNullOrEmpty())
                   R.drawable.placeholder
                else
                    book.volumeInfo?.imageLinks?.thumbnail
            )
            .crossfade(true)
            .placeholder(
                R.drawable.placeholder
            )
            .build()
    )
    val favorite = SwipeAction(

        icon = {
            Box(
                Modifier.size(96.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.TwoTone.Favorite, contentDescription = null,Modifier.size(48.dp))
            }
        },
        background = Color.Red,
        onSwipe = {
            isFavorite = !isFavorite
            //todo change db

        },

    )
    SwipeableActionsBox(
        endActions = listOf(favorite),
        swipeThreshold = 150.dp
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Color.LightGray
                )
                .padding(8.dp)
                .shadow(
                    4.dp,
                    RoundedCornerShape(8.dp),
                    spotColor = if (isFavorite) Color.Red else Color.DarkGray
                )
                ,

            ) {

            Row(
                Modifier
                    .fillMaxSize()
                    .background(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Transparent
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,


            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = book.volumeInfo?.title ?: "No title.",
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = book.volumeInfo?.description
                            ?: "No description.",
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                }

                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .border(4.dp, Color.Black)
                    )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    BookFinderTheme {
        BookItem(book = Book("", "1", "kind1", SearchInfo(""), "", null))
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemInScreenPreview() {
    BookFinderTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                //verticalArrangement = Arrangement.Center
            ) {
                BookItem(book = Book("", "1", "kind1", SearchInfo(""), "", null))
            }

        }

    }
}