import android.content.Context
import android.view.LayoutInflater
import com.example.charts.R
import com.example.charts.databinding.CustomBottomSheetDialogBinding
import com.example.charts.ui.dashboard.DashboardListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class CustomBottomSheetDialog(
    context: Context, private val adapter: DashboardListAdapter
) : BottomSheetDialog(context) {

    private val binding: CustomBottomSheetDialogBinding =
        CustomBottomSheetDialogBinding.inflate(LayoutInflater.from(context))

    init {
        setContentView(binding.root)

        binding.telegramIcon.setOnClickListener {
            adapter.addItem(R.drawable.telegramlogo, "telegram")
            dismiss()
        }

        binding.whatsappIcon.setOnClickListener {
            adapter.addItem(R.drawable.whatsapplogo,"whatsapp")
            dismiss()
        }

        binding.facebookIcon.setOnClickListener {
            adapter.addItem(R.drawable.facebooklogo, "facebook")
            dismiss()
        }
        binding.webLogo.setOnClickListener {
            adapter.addItem(R.drawable.weblogo, "website")
            dismiss()
        }
        binding.phone.setOnClickListener {
            adapter.addItem(R.drawable.phonelogo, "phone")
            dismiss()
        }

        binding.instagramIcon.setOnClickListener {
            adapter.addItem(R.drawable.instagramlogo,"instagram")
            dismiss()
        }

        binding.emailLogo.setOnClickListener {
            adapter.addItem(R.drawable.emaillogo, "facebook")
            dismiss()
        }
    }
}
