import diff_match_patch as dmp_module
import subprocess

dmp = dmp_module.diff_match_patch()
dir = ""
with open("data/base.bundle") as old, open("data/expected.bundle") as fresh:
    oldContent = old.read()
    freshContent = fresh.read()
    diff = dmp.diff_main(oldContent, freshContent)
    dmp.diff_cleanupSemantic(diff)
    patches = dmp.patch_make(oldContent, diff)
    with open("data/patch.txt", 'w') as output:
        output.write(''.join(list(map(lambda x: str(x).replace("\n", "\\n")+"\n", patches))))
