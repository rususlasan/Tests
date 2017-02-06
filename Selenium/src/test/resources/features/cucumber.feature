Feature: Google Search

Scenario: Word search
          Given Open page by url "https://www.google.ru" in browser
          Given Type to search input "adsterra"
          When Press button search
          Then Header search snippet "1" text start with "Adsterra"