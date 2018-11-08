# DiffMatchPatchTest
Test case for DiffMatchPatch library

Data is prepared in `data` folder.
Patch is created with `Python3` implementation of DiffMatchPatch, then patch is stored in file, then it's red from file and applied with Java implementation of DiffMatchPatch. 
To check that patches in file are valid: 

```
mv data/patch.txt data/patch.txt_tmp
python3 scripts/create_patch.py
diff data/patch.txt data/patch.txt_tmp
```

`data/base.bundle` and `data/expected.bundle` are ReactNative JS bundles built with Metro bundler.

Expected result: after applying patch result string will be equal to content of `data/expected.bundle`.
Actual result: after applying patch result string has differenct with content of `data/expected.bundle`: 
- in column 592923 expected symbol is `y`, actual symbol is `b`
