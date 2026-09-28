# Reset script wiring

`reset.mcr` opens Windows Search and searches for `ResetA` to launch the
`ResetAsphalt` Start Menu shortcut. `MacroCombiner.resets()` includes this macro
in the generated reset sequences.

The script is located at:

```text
C:\Users\ybao0\OneDrive\Desktop\Macro\reset.ps1
```

The shortcut at `%APPDATA%\Microsoft\Windows\Start Menu\Programs\ResetAsphalt.lnk`
must use these settings:

- Target: `C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe`
- Arguments: `-NoProfile -ExecutionPolicy Bypass -WindowStyle Hidden -File "C:\Users\ybao0\OneDrive\Desktop\Macro\reset.ps1"`
- Working directory: `C:\Users\ybao0\OneDrive\Desktop\Macro`

If the script moves again, update the shortcut's arguments and working directory.
The script path is not embedded in the Java sources or recorded macro.
