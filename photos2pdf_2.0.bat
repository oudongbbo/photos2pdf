@echo off

set /p a=��������Դ����Ŀ¼��ֱ�ӽ��ļ������������ɣ�:
echo %a%

set /p b=���������Ŀ¼��ֱ�ӽ��ļ������������ɣ�:
echo %b%

set /p c=�����pdf����:
echo %c%


java -jar photos2pdf_2.0.jar %a%  %b% %c%

pause
# pause ��ͣ