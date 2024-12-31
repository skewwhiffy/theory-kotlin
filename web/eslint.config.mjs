import * as react from 'eslint-plugin-react';
import * as eslintJs from "typescript-eslint";
import * as tseslint from "typescript-eslint";
import * as eslintReact from "typescript-eslint";

export default [
  {
    ignores: ['src/api']
  },
  ...eslintJs.configs.recommended,
  ...tseslint.configs.recommended,
  ...eslintReact.configs.recommended,
  {
    plugins: {
      "@eslint-react": react
    },
    rules: {},
  }
];

// export default [
//   eslintJs.configs.recommended,
//   tseslint.configs.recommended,
//   eslintReact.configs.recommended,
//   {
//     files: ["**/*.ts", "**/*.tsx"],
//     languageOptions: {
//       parser: tseslint.parser,
//       parserOptions: {
//         projectService: true,
//       },
//     },
//     rules: {
//       "@eslint-react/prefer-shorthand-boolean": "warn",
//     },
//   }
// ]