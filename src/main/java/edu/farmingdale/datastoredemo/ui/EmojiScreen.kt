package edu.farmingdale.datastoredemo.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.farmingdale.datastoredemo.R

import edu.farmingdale.datastoredemo.data.local.LocalEmojiData

/*
 * Screen level composable
 */
@Composable
fun EmojiReleaseApp(
    emojiViewModel: EmojiScreenViewModel = viewModel(
        factory = EmojiScreenViewModel.Factory
    )
) {
    EmojiScreen(
        uiState = emojiViewModel.uiState.collectAsState().value,
        selectLayout = emojiViewModel::selectLayout,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmojiScreen(
    uiState: EmojiReleaseUiState,
    selectLayout: (Boolean) -> Unit
) {
    val isLinearLayout = uiState.isLinearLayout
    //Added line to get UI state for dark theme
    val isDarkTheme = uiState.isDarkTheme
    // Added variable for state of checked box
    var chkd by remember { mutableStateOf(isDarkTheme) }
    // Modifier for background color
    val modifier = Modifier.background(if (chkd) Color.Black else Color.White)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.top_bar_name)) },
                actions = {
                    Checkbox(checked = chkd, onCheckedChange = { chkd=it })
                    val modifier = Modifier.background(if (chkd) Color.Black else Color.White)
                    if (chkd == true){
                        val modifier = Modifier.background(color = Color.Black)
                    }
                    IconButton(
                        onClick = {
                            selectLayout(!isLinearLayout)
                        }
                    ) {
                        Icon(
                            painter = painterResource(uiState.toggleIcon),
                            contentDescription = stringResource(uiState.toggleContentDescription),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary
                )
            )
        }
    ) { innerPadding ->
        val modifier = Modifier
            .padding(
                top = dimensionResource(R.dimen.padding_medium),
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium),
            )
        if (isLinearLayout) {
            EmojiReleaseLinearLayout(
                modifier = modifier.fillMaxWidth(),
                contentPadding = innerPadding
            )
        } else {
            EmojiReleaseGridLayout(
                modifier = modifier,
                contentPadding = innerPadding,
            )
        }
    }
}

@Composable
fun EmojiReleaseLinearLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val cntxt = LocalContext.current
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        items(
            items = LocalEmojiData.EmojiList,
            key = { e -> e }
        ) { e ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium,
                //Made the emojis clickable, and added toast comments for each one
                modifier = Modifier.clickable {
                    val emojiDescription = when(e) {
                        "😀" -> "Smiling Emoji"
                        "😃" -> "Happy Emoji"
                        "😄" -> "Laughing Emoji"
                        "😁" -> "Grinning Emoji"
                        "😆" -> "Laughing with eyes closed Emoji"
                        "😅" -> "Grinning with sweat Emoji"
                        "😂" -> "Laughing and Crying Emoji"
                        "🤣" -> "Rolling on the floor Emoji"
                        "😊" -> "Smiling with Blush Emoji"
                        "😇" -> "Smiling with Halo Emoji"
                        "🙂" -> "Calm Smile Emoji"
                        "🙃" -> "Upside down smile Emoji"
                        "😉" -> "Winking Smile Emoji"
                        "😌" -> "Eyes closes smile Emoji"
                        "😍" -> "Heart eyes smile Emoji"
                        "🥰" -> "Smile with Hearts Emoji"
                        "😘" -> "Blowing a kiss Emoji"
                        "😗" -> "Kissing face eyes open Emoji"
                        "😙" -> "Kissing face with eyes closed Emoji"
                        "😚" -> "Kissing face with blush Emoji"
                        "😋" -> "Licking lips Emoji"
                        "😛" -> "Tongue out Emoji"
                        "😝" -> "Eyes closed tongue out Emoji"
                        "😜" -> "Wink with tongue out Emoji"
                        "🤪" -> "Silly face Emoji"
                        "🤨" -> "Confused face Emoji"
                        "🧐" -> "Thinking face Emoji"
                        "🤓" -> "Nerd Emoji"
                        "😎" -> "Cool Emoji"
                        "🤩" -> "Star Eyes Emoji"
                        "🥳" -> "Party Emoji"
                        "😏" -> "Smirking Emoji"
                        "😒" -> "Uninterested Emoji"
                        "😞" -> "Disappointed Emoji"
                        "😔" -> "Sad Emoji"
                        "😟" -> "Frowning Emoji"
                        "😕" -> "Slightly Frowning Emoji"
                        "🙁" -> "Frowning with no Eyebrows Emoji"
                        "☹️" -> "Super Sad Emoji"
                        "😣" -> "Struggling Emoji"
                        "😖" -> "Frustrated Face"
                        "😫" -> "Screaming in Agony Emoji"
                        "😩" -> "Screaming in Exhaustion Emoji"
                        "🥺" -> "Begging Emoji"
                        "😢" -> "Tear Rolling Down Cheek Emoji"
                        "😭" -> "Weeping Emoji"
                        "😤" -> "Steam from nose Emoji"
                        "😠" -> "Angry Emoji"
                        "😡" -> "Red Angry Emoji"
                        "🤬" -> "Angry Expletives Emoji"
                        "😈" -> "Happy Demon Emoji"
                        "👿" -> "Angry Demon Emoji"
                        "💀" -> "Skull Emoji"
                        "☠️" -> "Crossbones Emoji"
                        "💩" -> "Poop "
                        "🤡" -> "Clown Emoji"
                        "👹" -> "Ogre Emoji"
                        "👺" -> "Goblin Emoji"
                        "👻" -> "Ghost Emoji"
                        "👽" -> "Alien Emoji"
                        "👾" -> "Spider Emoji"
                        "🤖" -> "Robot Emoji"
                        "😺" -> "Cat Face Emoji"
                        "😸" -> "Laughing Cat face Emoji"
                        "😹" -> "Crying Laughing Cat Emoji"
                        "😻" -> "Heart Eyes Cat Emoji"
                        "😼" -> "Sly Cat Smile"
                        "😽" -> "Smooching Cat"
                        "🙀" -> "Shocked Cat"
                        "😿" -> "Teary Cat"
                        "😾" -> "Stern Cat"
                        "🙈" -> "Monkey with Eyes covered"
                        "🙉" -> "Monkey with hands to side"
                        "🙊" -> "Monkey with hand on mouth"
                        "💋" -> "Lip Emoji"
                        "💌" -> "Heart Mail Emoji"
                        "💘" -> "Arrow through heart Emoji"
                        "💝" -> "Gift Heart Emoji"
                        "💖" -> "Star Heart Emojo"
                        "💗" -> "Thumping Heart Emoji"
                        "💓" -> "Heart with hue"
                        "💞" -> "Two circling hearts"
                        "💕" -> "Two hearts"
                        "💟" -> "White Heart"
                        "❣️" -> "Red Heart"
                        "👍" -> "Thumbs Up"
                        "👎" -> "Thumbs Down"
                        "✊" -> "Fist Up"
                        "👊" -> "Fist"
                        "✌️" -> "Peace Sign"
                        else -> {"None"}
                    }
                    //Format for toast comments
                    Toast.makeText(cntxt, emojiDescription, Toast.LENGTH_SHORT).show();
                    Toast.makeText(cntxt, e, Toast.LENGTH_SHORT).show();
                }
            ) {
                    Text(
                        text = e, fontSize = 50.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_medium)),
                        textAlign = TextAlign.Center
                    )


            }
        }
    }
}

@Composable
fun EmojiReleaseGridLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    //Added local context value
    val cntxt = LocalContext.current
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(
            items = LocalEmojiData.EmojiList,
            key = { e -> e }
        ) { e ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                // Makes it clickable and added toast comments
                modifier = Modifier.height(110.dp).clickable {
                    val emojiDescription = when(e) {
                        "😀" -> "Smiling Emoji"
                        "😃" -> "Happy Emoji"
                        "😄" -> "Laughing Emoji"
                        "😁" -> "Grinning Emoji"
                        "😆" -> "Laughing with eyes closed Emoji"
                        "😅" -> "Grinning with sweat Emoji"
                        "😂" -> "Laughing and Crying Emoji"
                        "🤣" -> "Rolling on the floor Emoji"
                        "😊" -> "Smiling with Blush Emoji"
                        "😇" -> "Smiling with Halo Emoji"
                        "🙂" -> "Calm Smile Emoji"
                        "🙃" -> "Upside down smile Emoji"
                        "😉" -> "Winking Smile Emoji"
                        "😌" -> "Eyes closes smile Emoji"
                        "😍" -> "Heart eyes smile Emoji"
                        "🥰" -> "Smile with Hearts Emoji"
                        "😘" -> "Blowing a kiss Emoji"
                        "😗" -> "Kissing face eyes open Emoji"
                        "😙" -> "Kissing face with eyes closed Emoji"
                        "😚" -> "Kissing face with blush Emoji"
                        "😋" -> "Licking lips Emoji"
                        "😛" -> "Tongue out Emoji"
                        "😝" -> "Eyes closed tongue out Emoji"
                        "😜" -> "Wink with tongue out Emoji"
                        "🤪" -> "Silly face Emoji"
                        "🤨" -> "Confused face Emoji"
                        "🧐" -> "Thinking face Emoji"
                        "🤓" -> "Nerd Emoji"
                        "😎" -> "Cool Emoji"
                        "🤩" -> "Star Eyes Emoji"
                        "🥳" -> "Party Emoji"
                        "😏" -> "Smirking Emoji"
                        "😒" -> "Uninterested Emoji"
                        "😞" -> "Disappointed Emoji"
                        "😔" -> "Sad Emoji"
                        "😟" -> "Frowning Emoji"
                        "😕" -> "Slightly Frowning Emoji"
                        "🙁" -> "Frowning with no Eyebrows Emoji"
                        "☹️" -> "Super Sad Emoji"
                        "😣" -> "Struggling Emoji"
                        "😖" -> "Frustrated Face"
                        "😫" -> "Screaming in Agony Emoji"
                        "😩" -> "Screaming in Exhaustion Emoji"
                        "🥺" -> "Begging Emoji"
                        "😢" -> "Tear Rolling Down Cheek Emoji"
                        "😭" -> "Weeping Emoji"
                        "😤" -> "Steam from nose Emoji"
                        "😠" -> "Angry Emoji"
                        "😡" -> "Red Angry Emoji"
                        "🤬" -> "Angry Expletives Emoji"
                        "😈" -> "Happy Demon Emoji"
                        "👿" -> "Angry Demon Emoji"
                        "💀" -> "Skull Emoji"
                        "☠️" -> "Crossbones Emoji"
                        "💩" -> "Poop "
                        "🤡" -> "Clown Emoji"
                        "👹" -> "Ogre Emoji"
                        "👺" -> "Goblin Emoji"
                        "👻" -> "Ghost Emoji"
                        "👽" -> "Alien Emoji"
                        "👾" -> "Spider Emoji"
                        "🤖" -> "Robot Emoji"
                        "😺" -> "Cat Face Emoji"
                        "😸" -> "Laughing Cat face Emoji"
                        "😹" -> "Crying Laughing Cat Emoji"
                        "😻" -> "Heart Eyes Cat Emoji"
                        "😼" -> "Sly Cat Smile"
                        "😽" -> "Smooching Cat"
                        "🙀" -> "Shocked Cat"
                        "😿" -> "Teary Cat"
                        "😾" -> "Stern Cat"
                        "🙈" -> "Monkey with Eyes covered"
                        "🙉" -> "Monkey with hands to side"
                        "🙊" -> "Monkey with hand on mouth"
                        "💋" -> "Lip Emoji"
                        "💌" -> "Heart Mail Emoji"
                        "💘" -> "Arrow through heart Emoji"
                        "💝" -> "Gift Heart Emoji"
                        "💖" -> "Star Heart Emojo"
                        "💗" -> "Thumping Heart Emoji"
                        "💓" -> "Heart with hue"
                        "💞" -> "Two circling hearts"
                        "💕" -> "Two hearts"
                        "💟" -> "White Heart"
                        "❣️" -> "Red Heart"
                        "👍" -> "Thumbs Up"
                        "👎" -> "Thumbs Down"
                        "✊" -> "Fist Up"
                        "👊" -> "Fist"
                        "✌️" -> "Peace Sign"
                        else -> {"None"}
                    }
                    //Shows the toast comments
                    Toast.makeText(cntxt, emojiDescription, Toast.LENGTH_SHORT).show();
                },
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(
                    text = e, fontSize = 50.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(dimensionResource(R.dimen.padding_small))
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
