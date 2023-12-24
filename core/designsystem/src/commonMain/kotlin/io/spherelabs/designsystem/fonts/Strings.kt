package io.spherelabs.designsystem.fonts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import io.spherelabs.resource.EnAnyPassStrings
import io.spherelabs.resource.strings.AnyPassStrings
import io.spherelabs.resource.strings.Strings

val LocalStrings: ProvidableCompositionLocal<AnyPassStrings> = compositionLocalOf {
  EnAnyPassStrings
}

@Composable
fun useStrings(
    languageTag: LanguageTag = "en",
): Lyricist<AnyPassStrings> = rememberStrings(Strings, languageTag)

@Composable
fun ProvideStrings(
    lyricist: Lyricist<AnyPassStrings> = useStrings(),
    content: @Composable () -> Unit,
) {
  ProvideStrings(lyricist, LocalStrings, content)
}
