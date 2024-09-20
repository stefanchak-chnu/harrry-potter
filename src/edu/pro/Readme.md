# Word Frequency Counter Refactoring

This document outlines the improvements made to the Word Frequency Counter program.

## Improvements

1. **Efficient Data Structure**
   - Replaced separate `distincts` and `freq` arrays with a single `Map<String, Long>` called `wordFrequencies`.
   - Allows for O(1) lookup time when counting word frequencies, compared to O(n) in the original approach.

2. **Stream-based Word Frequency Counting**
   - Used `Arrays.stream(words)` to create a stream of words.
   - Applied `collect(Collectors.groupingBy())` to count frequencies in a single pass.
   - Replaces nested loops, reducing time complexity from O(n^2) to O(n).

3. **Filtering Empty Words**
   - Added `.filter(word -> !word.isEmpty())` to exclude empty strings.
   - Handles cases where multiple consecutive spaces in the input might create empty words.

4. **Sorting and Limiting Results**
   - Used `wordFrequencies.entrySet().stream()` to create a stream of map entries.
   - Applied `sorted(Map.Entry.<String, Long>comparingByValue().reversed())` for descending order sort.
   - Used `limit(30)` to get only the top 30 results.

5. **Simplified Output**
   - Used `forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()))` for direct printing.
   - Eliminates the need for a final output loop.

6. **Removed Unnecessary Steps**
   - Eliminated creation and population of `distinctString`.
   - Removed unnecessary initial `Arrays.sort(words)` operation.

7. **Improved Regular Expression**
   - Changed `content.split(" +")` to `content.split("\\s+")` to handle all whitespace characters.

8. **Code Conciseness**
   - Reduced overall line count while maintaining readability.
   - Eliminated several intermediate steps and variables.

9. **Memory Efficiency**
   - Removed need for additional arrays (`distincts` and `freq`), reducing memory usage.

10. **Type Safety**
    - Use of generics in Map and stream operations provides better type safety compared to raw arrays.

11. **Readability**
    - Stream-based approach more clearly expresses the intent of the code.
    - Reduced nesting depth, making the code easier to follow.
12. **Single Responsibility Principle**
    - Main method does too much, moved related code to separate methods which do one thing.

## Result

These improvements result in code that is more efficient, more robust, easier to read and maintain, and more aligned with modern Java programming practices.