# Project instructions

## Workflow

- Before implementing, inspect similar code in the project and follow its conventions.
- After feedback, reconsider the affected data-to-UI flow rather than only rewriting the flagged expression.
- Review the complete diff for consistency, including function signatures and call sites, before reporting completion.

## Kotlin

- Do not add functions that merely wrap constructor calls. Call the constructor directly.
- Prefer ordinary statements and operators over scope functions.
- Do not use default parameter values.
- Do not add extension functions to project-owned classes. Put the behavior in the class itself.

## Models and mapping

- Normalize API data and construct derived URLs in mappers. UI models should receive prepared values.
- Put shared model logic in model methods instead of duplicating it in UI or adding UI-layer extensions.

## Compose

- Use explicit `if` statements for conditional UI. Do not use scope functions such as `let` to emit UI.
- Calculate display values and visibility conditions before UI calls. Keep composable arguments simple.
- Name booleans after the actual display condition, for example `showUsernameBelowName`, rather than `hasUsername` when multiple fields determine visibility.
- Keep sheet state, animations, coroutines, and dismissal handling inside a dedicated sheet component.
