package io.spherelabs.anypass.ui.onboarding



//@Composable
//fun OnboardingRoute(
//    viewModel: OnboardingViewModel = useInject(),
//    navigateToPassword: () -> Unit,
//) {
//
//    val onboardingState = viewModel.state.collectAsStateWithLifecycle()
//
//    OnboardingScreen(
//        wish = { newWish ->
//            viewModel.wish(newWish)
//        },
//        state = onboardingState.value,
//        flow = viewModel.effect,
//        navigateToPassword = {
//            navigateToPassword.invoke()
//        },
//    )
//}
//
//@Composable
//fun OnboardingScreen(
//    modifier: Modifier = Modifier,
//    wish: (OnboardingWish) -> Unit,
//    state: OnboardingState,
//    flow: Flow<OnboardingEffect>,
//    navigateToPassword: () -> Unit,
//) {
//
//    LaunchedEffect(key1 = true) {
//        flow.collectLatest {
//            when (it) {
//                OnboardingEffect.SignUp -> {
//                    navigateToPassword.invoke()
//                }
//
//                else -> {}
//            }
//        }
//    }
//
//    Column(
//        modifier = modifier.fillMaxSize().background(color = BlackRussian),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//
//        when {
//            state.isLogged -> {
//                useEffect(true) {
//                    navigateToPassword.invoke()
//
//                }
//            }
//
//            state.isLoading -> {
//                CircularProgressIndicator(
//                    modifier = modifier.align(Alignment.CenterHorizontally),
//                )
//            }
//
//            state.isFirstTime -> {
//                Spacer(modifier = modifier.height(24.dp))
//
//                OnboardingImage(
//                    modifier = modifier.fillMaxWidth().weight(1f)
//                        .padding(start = 24.dp, end = 24.dp),
//                )
//
//                OnboardingHeadline()
//
//                OnboardingDescription(modifier)
//
//                Spacer(modifier.height(24.dp))
//
//                GetStartedButton(modifier) { wish.invoke(OnboardingWish.GetStartedClick) }
//
//                Spacer(modifier = modifier.height(24.dp))
//            }
//        }
//
//    }
//}
//
//@Composable
//private fun OnboardingImage(
//    modifier: Modifier = Modifier,
//) {
//    Image(
//        modifier = modifier,
//        painter = painterResource(MR.images.illustration),
//        contentDescription = null,
//    )
//}
//
//@Composable
//private fun OnboardingHeadline() {
//    Text(
//        text = LocalStrings.current.onboardingHeadline,
//        fontSize = 32.sp,
//        color = Color.White,
//        fontFamily = GoogleSansFontFamily,
//        fontWeight = FontWeight.Medium,
//    )
//}
//
//@Composable
//private fun OnboardingDescription(
//    modifier: Modifier = Modifier,
//) {
//    Text(
//        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
//        text = LocalStrings.current.onboardingDescription(),
//        fontSize = 18.sp,
//        fontFamily = GoogleSansFontFamily,
//        fontWeight = FontWeight.Medium,
//        color = Color.White.copy(0.5F),
//        textAlign = TextAlign.Center,
//    )
//}
//
//@Composable
//private fun GetStartedButton(
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit,
//) {
//    Button(
//        modifier = modifier
//            .fillMaxWidth().height(65.dp).padding(start = 24.dp, end = 24.dp),
//        shape = RoundedCornerShape(16.dp),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = LavenderBlue.copy(0.3f),
//        ),
//        onClick = {
//            onClick.invoke()
//        },
//    ) {
//        GetStartedText()
//    }
//}
//
//@Composable
//private fun GetStartedText() {
//    Text(
//        text = LocalStrings.current.getStarted,
//        fontFamily = GoogleSansFontFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 24.sp,
//        color = Color.White,
//    )
//}
