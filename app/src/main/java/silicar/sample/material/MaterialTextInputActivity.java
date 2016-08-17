package silicar.sample.material;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import silicar.sample.R;
import silicar.sample.databinding.ActivityMaterialTextInputBinding;

public class MaterialTextInputActivity extends AppCompatActivity {

    ActivityMaterialTextInputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_material_text_input);
        binding.user.setHint("用户名/邮箱");
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.user.setError("用户不存在");
                TextInputEditText psdEdt = (TextInputEditText) binding.password.getEditText();
                psdEdt.setError("密码错误");
                binding.password.setError("密码错误");
                binding.password.setErrorEnabled(false);
            }
        });
    }
}
