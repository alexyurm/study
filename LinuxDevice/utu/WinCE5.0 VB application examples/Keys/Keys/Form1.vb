Public Class Form1

    Dim str1 As String

    Private Sub Form1_KeyDown(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyDown
        str1 = e.KeyCode.ToString()
        TextBox1.Text = str1 & " key is pressed."
    End Sub

    Private Sub Form1_KeyUp(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyUp
        str1 = e.KeyCode.ToString()
        TextBox1.Text = str1 & " key is released."
    End Sub

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

End Class
