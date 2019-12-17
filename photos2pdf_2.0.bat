@echo off

set /p a=请输入资源所在目录（直接将文件夹拉进来即可）:
echo %a%

set /p b=请输入输出目录（直接将文件夹拉进来即可）:
echo %b%

set /p c=输出的pdf名字:
echo %c%


java -jar photos2pdf_2.0.jar %a%  %b% %c%

pause
# pause 暂停