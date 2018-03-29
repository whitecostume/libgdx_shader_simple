#ifdef GL_ES
precision mediump float;
#endif
varying vec2 v_texCoords;
uniform float widthStep;
uniform float heightStep;
uniform float strength;
uniform sampler2D u_texture;
const float blurRadius = 5.0;
const float blurPixels = (blurRadius * 2.0 + 1.0) * (blurRadius * 2.0 + 1.0);

void main()
{
    vec3 sumColor = vec3(0.0, 0.0, 0.0);
    for(float fy = -blurRadius; fy <= blurRadius; ++fy)
    {
        for(float fx = -blurRadius; fx <= blurRadius; ++fx)
        {
            vec2 coord = vec2(fx * widthStep, fy * heightStep);
            sumColor += texture2D(u_texture, v_texCoords + coord).rgb;
        }
    }
    gl_FragColor = vec4(mix(texture2D(u_texture, v_texCoords).rgb, sumColor / blurPixels, strength), 1.0);
}