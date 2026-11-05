# Project instructions

## Workflow

- Add conditions only for concrete scenarios required by the task or observed in practice; first check whether the data already guarantees the desired behavior.
- Prefer the smallest change that satisfies the task. Reuse the existing data flow and UI structure; avoid intermediate representations that are immediately converted back.
- Check implementation choices and defaults against the user's stated goal. A configurable solution must fulfill that goal in its default behavior, without requiring extra setup.
- Before implementing, search for the behavior being changed across the project, including other modules. Inspect a comparable implementation and its data-to-UI flow; do not assume the affected file establishes the project convention.
- After feedback, reconsider the affected data-to-UI flow rather than only rewriting the flagged expression.
- Evaluate proposals against the user's established goals and explain coverage limits. When challenged, reassess using concrete reasoning; do not reverse a conclusion solely to agree with the user.
- Review the complete diff for consistency, including function signatures and call sites, before reporting completion.

## Kotlin

- Keep each function focused on one responsibility. Separate distinct operations into separate functions.
- Keep calls with a single argument on one line; do not put the argument and closing parenthesis on separate lines.
- Do not add functions that merely wrap constructor calls. Call the constructor directly.
- Prefer ordinary statements and operators over scope functions.
- Access object properties directly instead of introducing local aliases. Keep local variables for computed values and named display conditions.
- Use explicit lambdas instead of callable references when passing callbacks, for example `onCloseDetails = { viewModel.onCloseDetails() }` instead of `onCloseDetails = viewModel::onCloseDetails`.
- Do not use default parameter values.
- Do not add extension functions to project-owned classes. Put the behavior in the class itself.

## Models and mapping

- Prepare state-dependent text in the ViewModel using `StringValue`. Update it together with the related screen state. Composables should only resolve it with `asString`, without choosing string resources or formatting arguments.
- Normalize API data and construct derived URLs in mappers. UI models should receive prepared values.
- Do not add defensive checks for hypothetical invalid input, including in tests and configuration. Base validation on explicit requirements or observed failures. When uncertain about an API contract, consult its official documentation.
- Put shared model logic in model methods instead of duplicating it in UI or adding UI-layer extensions.

## Compose

- Preserve component responsibilities. Reconsider composition instead of widening input models to accommodate unrelated data.
- Use explicit `if` statements for conditional UI. Do not use scope functions such as `let` to emit UI.
- Calculate display values and visibility conditions before UI calls. Keep composable arguments simple.
- Name booleans after the actual display condition, for example `showUsernameBelowName`, rather than `hasUsername` when multiple fields determine visibility.
- Keep sheet state, animations, coroutines, and dismissal handling inside a dedicated sheet component.
