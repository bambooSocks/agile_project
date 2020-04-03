# CI rules

### What doesn't belong to master branch

1. Code that causes undefined or failing tests. (Implement all your tests)
2. Commented out code ("saved for later" or test code)
3. Unformatted code (always run `Ctrl+Shift+F`)



### Checklist before making changes to master

1. PULL CHANGES!!!
2. write tests
3. make them succeed (write production code that doesn't fail)
4. document the new code (Java Docs)
5. PULL CHANGES!!!
6. RUN TEST AGAIN
7. fix every new error even if it is not your code
8. clean code of test code and random commended out stuff
9. run `Ctrl+Shift+F`
10. check for changes if there are changes go to step 5 otherwise continue
11. push and hope that you didn't forget any of the steps



### Convention for feature branch naming

every feature branch name should look like `feature_<feature_notation>_<feature_name>`. For example `feature_M1_register_client`. Keep them short and succinct.



