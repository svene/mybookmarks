@import org.svenehrke.mybookmarks.FragmentHelper

@param FragmentHelper fragmentHelper
!{var M = FragmentHelper.CsvTextfieldModel.build(fragmentHelper); }

<textarea id="csvString" hidden class="preview-csv">${M.csvString()}</textarea>
